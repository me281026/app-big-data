package com.app.data.clean.flink;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.alibaba.druid.pool.DruidPooledConnection;
import com.alibaba.fastjson.JSONObject;
import com.app.data.clean.entity.AddressDetail;
import com.app.data.clean.entity.Keymans;
import com.app.data.clean.entity.Shareholders;
import com.app.data.clean.service.MD5Util;
import com.app.data.clean.service.SendMailUtil;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.async.ResultFuture;
import org.apache.flink.streaming.api.functions.async.RichAsyncFunction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

//import org.apache.flink.streaming.api.functions.async.collector.AsyncCollector;

public class AsyncMySQLData extends RichAsyncFunction<String,String> implements Serializable {


    private Logger logger = LoggerFactory.getLogger(AsyncMySQLData.class);


    private transient DruidDataSource dataSource;
    private transient PreparedStatement preparedStatement;
    private transient PreparedStatement preparedStatementId;
    private transient DruidPooledConnection connection;
    private static String drivername = "com.mysql.jdbc.Driver";
    //private static String dburl = "jdbc:mysql://192.168.20.160:3306/app_dev";
    //private static String dburl = "jdbc:mysql://192.168.20.160:3306/app2?createDatabaseIfNotExist=true&amp;useSSL=true";
    private static String dburl = "jdbc:mysql://101.89.196.246:3306/app2";

    @Override
    public void open(Configuration parameters) throws Exception {
        super.open(parameters);
        Properties properties = new Properties();
        properties.put("driverClassName",drivername);
        properties.put("username","root");
        //properties.put("password","abc123");
        properties.put("password","app@20170604");
        properties.put("url",dburl);
        properties.put("minIdle","3");
        properties.put("initialSize","3");
        properties.put("maxActive","20");
        properties.put("maxWait","60000");
        properties.put("validationQuery","SELECT 'x'");
        properties.put("testOnBorrow","true");
        properties.put("testWhileIdle","true");
        properties.put("timeBetweenEvictionRunsMillis","60000");
        properties.put("poolPreparedStatements","true");
        properties.put("maxPoolPreparedStatementPerConnectionSize","20");
        dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
        connection = dataSource.getConnection();
    }

    @Override
    public void asyncInvoke(String message, ResultFuture<String> asyncCollector) throws Exception {

        try {
            JSONObject jsonObject = JSONObject.parseObject(message);
            com.app.data.clean.entity.DealData crawlerData = JSONObject.toJavaObject(jsonObject, com.app.data.clean.entity.DealData.class);
            String company_info_id = jsonObject.getString("company_info_id");
            String company_name = crawlerData.getCompanyAll().getCompany_name();
            //获取经纬信息
            AddressDetail addressDetail = crawlerData.getAddressDetail();

            List<Shareholders> shareholders = new ArrayList<>();
            List<Keymans> keymanslist = new ArrayList<>();
            if (message.contains("onlyshareholders")) {
                //获取股东信息
                shareholders = crawlerData.getShareholders();
                //只含股东,加入主要人员中
                for (Shareholders shareholder:shareholders) {
                    //股东为公司之类的去掉
                    if (shareholder.getShareholder().length() > 5)
                        continue;

                    Keymans keymans = new Keymans(shareholder.getShareCompanyNum(),"",shareholder.getShareholder(),shareholder.getShareLink(),"股东");
                    keymanslist.add(keymans);
                }
            } else if (message.contains("onlykeymans")) {
                //主要人员信息

                keymanslist = crawlerData.getKeymans();
            } else {
                //获取股东信息
                shareholders = crawlerData.getShareholders();
                //主要人员信息
                keymanslist = crawlerData.getKeymans();
                //判断比较股东信息和主要人员信息
                for (Shareholders shareholder:shareholders) {
                    String shareName = shareholder.getShareholder();
                    String shareLink = shareholder.getShareLink();
                    //如果是公司则进行下一步
                    if (shareLink.contains("company")) {
                        continue;
                    }
                    //比较存放主要人员姓名
                    ArrayList<String> strings = new ArrayList<>();
                    for (Keymans keyman:keymanslist) {
                        String keymanJob = keyman.getKeymanJob();
                        String keymanName = keyman.getKeymanName();
                        //姓名相同并且不存在职位
                        if (keymanName.equals(shareName)) {
                            if (keymanJob == null || keymanJob.equals("") || keymanJob.equals("-")) {
                                keyman.setKeymanJob("股东");
                            }
                        }
                        //当名字存在时加入
                        if (keymanName != null || !keymanName.equals("") || !keymanName.equals("-")) {
                            strings.add(keymanName);
                        }

                    }
                    //股东中存在但是主要人员不存在
                    if (!strings.contains(shareName)) {
                        //股东为公司之类的去掉
                        if (shareholder.getShareholder().length() > 5)
                            continue;

                        //存入keymanslist
                        Keymans keymans = new Keymans(shareholder.getShareCompanyNum(),"",shareholder.getShareholder(),shareholder.getShareLink(),"股东");
                        //加入主要人员
                        keymanslist.add(keymans);
                    }
                }
            }


            //结果插入
            ArrayList<com.app.data.clean.entity.KeyMan> listKeyMan = new ArrayList<>();

            //判断标记
            String flag = "";

            Boolean next_one = true;

            //遍历查询
            for (Keymans keymans:keymanslist) {

                //去掉公司
                String keymanName = keymans.getKeymanName();
                if (keymanName.equals("") || keymanName == null || keymanName.equals("-") || keymanName.contains("公司")) {
                    continue;
                }

                com.app.data.clean.entity.KeyMan keyMan = new com.app.data.clean.entity.KeyMan();
                keyMan.setKeymanCompanyNum(Long.parseLong(keymans.getKeymanCompanyNum()));
                keyMan.setKeymanImg(keymans.getKeymanImg());
                keyMan.setKeymanJob(keymans.getKeymanJob());
                keyMan.setKeymanLink(keymans.getKeymanLink());
                keyMan.setKeymanName(keymanName);
                //判断是股东还是主要人员
                if (keymans.getKeymanJob().equals("股东")) {
                    keyMan.setPeople_type("1");
                } else {
                    keyMan.setPeople_type("2");
                }

                if (addressDetail != null) {
                    //设置经纬度信息,区域信息
                    keyMan.setProvince_id(addressDetail.getProvince_id());
                    keyMan.setCity_id(addressDetail.getCity_id());
                    keyMan.setArea_id(addressDetail.getArea_id());
                    keyMan.setComp_lat(addressDetail.getCompLat());
                    keyMan.setComp_lng(addressDetail.getCompLng());
                }


                //设置参数
                keyMan.setCompany_name(company_name);
                String idSql = "SELECT id,bonding FROM sys_company WHERE company_info_id = ?";
                preparedStatementId = this.connection.prepareStatement(idSql);
                preparedStatementId.setString(1,company_info_id);
                ResultSet resultSetId = preparedStatementId.executeQuery();
                if (resultSetId.next()) {
                    Long company_id = resultSetId.getLong("id");
                    flag = resultSetId.getString("bonding");
                    //判断是否为认证用户
                    if (null != flag && flag.equals("1")) {
                        //当前公司存在认证数据
                        next_one = false;
                        break;
                    }
                    keyMan.setCompany_id(company_id);
                } else {
                    keyMan.setCompany_id(0l);
                }


                //生成human_c_id
                StringBuilder stringBuilder = new StringBuilder();
                String md5_str = stringBuilder.append(company_name).append(keymans.getKeymanName()).toString();
                //String md5_str = stringBuilder.append(company_name).append(keymans.getKeymanName()).toString();
                //先通过三段md5拿到uuid
                /*String company_md5_id = MD5Util.string2MD5(company_name);
                String job_md5_id = MD5Util.string2MD5(keymans.getKeymanJob());
                String name_md5_id = MD5Util.string2MD5(keymans.getKeymanName());*/
                String humancid = MD5Util.MD5Encode(md5_str,"utf8");
                //String uuid = UUID.randomUUID().toString().replaceAll("-", "");
                //String humancid = keyMan.getKeymanLink().replaceAll("https://www.tianyancha.com/human/", "");
                //String sql = "SELECT human_c_id FROM bs_addressbook WHERE human_c_id = ?";
                //判断状态,初始状态为0
                String sql = "SELECT human_c_id,job_title,mobile FROM bs_addressbook WHERE user_name = ? and company_name = ?";
                preparedStatement = this.connection.prepareStatement(sql);
                preparedStatement.setString(1,keymans.getKeymanName());
                preparedStatement.setString(2,company_name);
                /*if (keyMan.getPeople_type().equals("2")) {
                    String sql = "SELECT human_c_id FROM flink_bs_addressbook WHERE human_c_id = ?";
                    preparedStatement = this.connection.prepareStatement(sql);
                    preparedStatement.setString(1,humancid);
                } else if (keyMan.getPeople_type().equals("1")) {
                    String sql = "SELECT human_c_id,job_title FROM flink_bs_addressbook WHERE company_name = ? and user_name = ?";
                    preparedStatement = this.connection.prepareStatement(sql);
                    preparedStatement.setString(1,keyMan.getCompany_name());
                    preparedStatement.setString(2,keyMan.getKeymanName());
                }*/
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    String human_c_id = resultSet.getString("human_c_id");
                    String job_title = resultSet.getString("job_title");
                    String mobile = resultSet.getString("mobile");
                        if (null != mobile && !mobile.equals("")) {
                            next_one = false;
                            keyMan.setStatus(3);
                        } else {
                            keyMan.setStatus(2);
                            keyMan.setHuman_c_id(human_c_id);
                        }
                        //是股东,修改职业
                        if (keyMan.getPeople_type().equals("1") && !job_title.equals("股东")) {
                            keyMan.setKeymanJob(job_title);
                        }
                } else {
                    keyMan.setStatus(1);
                    keyMan.setHuman_c_id(humancid);
                }
                listKeyMan.add(keyMan);
            }

            //判断是否为认证用户
            if (next_one) {
                //收集
                String jsonS = JSONObject.toJSONString(listKeyMan);
                asyncCollector.complete(Collections.singleton(jsonS));
                //asyncCollector.collect(Collections.singleton(keyMan));
            }

            logger.info("打开MySQL连接");
        }catch (Exception e) {
            logger.error(e.getMessage());
            ////SendMailUtil.send("zww@app-tech.com", "MySQL Exception Info", e.getMessage());
        }
    }

    @Override
    public void close() throws Exception {
        super.close();
        //关闭连接和释放资源
        if (connection != null) {
            connection.close();
        }
        if (preparedStatement != null) {
            preparedStatement.close();
        }
        if (preparedStatementId != null) {
            preparedStatementId.close();
        }
    }



    //当异步I / O请求超时时，默认情况下会引发异常并重新启动作业。如果要处理超时，可以覆盖该AsyncFunction#timeout方法
    /*@Override
    public void timeout(String input, ResultFuture<KeyMan> resultFuture) throws Exception {

    }*/
}
