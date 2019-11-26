package com.app.data.clean.flink;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.alibaba.druid.pool.DruidPooledConnection;
import com.alibaba.fastjson.JSONObject;
import com.app.data.clean.entity.KeyMan;
import com.app.data.clean.service.SendMailUtil;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.sink.RichSinkFunction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.util.Date;
import java.util.List;
import java.util.Properties;

public class AddressBookWriter extends RichSinkFunction<String> implements Serializable {

    private static Logger logger = LoggerFactory.getLogger(AddressBookWriter.class);

    private transient DruidDataSource dataSource;
    private transient PreparedStatement preparedStatement;
    private transient DruidPooledConnection connection;
    private static String drivername = "com.mysql.jdbc.Driver";
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
    public void invoke(String listKeyManStr) throws Exception {

        List<KeyMan> listKeyMan = JSONObject.parseArray(listKeyManStr, KeyMan.class);

        for (KeyMan keyMan:listKeyMan) {
            //KeyMan keyMan = (KeyMan) object;
            //判断状态
            Integer status = keyMan.getStatus();
            String sql = "";
            Date dt = new Date();
            java.text.SimpleDateFormat sdf =
                    new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String currentTime = sdf.format(dt);
            Long company_id = keyMan.getCompany_id();
            //需要insert
            if (status == 1) {
                sql = "insert into bs_addressbook(id,user_id,user_name,user_head,mobile,job_title,status,company_id,company_name,certi_status,industry_id,province_id,city_id,area_id,comp_lat,comp_lng,delete_flag,code,created_time,updated_time,is_vip,import_data,human_c_id,accessed) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
                preparedStatement = this.connection.prepareStatement(sql);
                preparedStatement.setLong(1,0l);
                preparedStatement.setLong(2,0l);
                preparedStatement.setString(3,keyMan.getKeymanName());
                preparedStatement.setString(4,"");
                preparedStatement.setString(5,"");
                preparedStatement.setString(6,keyMan.getKeymanJob());
                preparedStatement.setString(7,"PASS");
                preparedStatement.setLong(8,company_id);
                preparedStatement.setString(9,keyMan.getCompany_name());
                preparedStatement.setString(10,"");
                preparedStatement.setLong(11,keyMan.getKeymanCompanyNum());
                preparedStatement.setString(12,keyMan.getProvince_id());
                preparedStatement.setString(13,keyMan.getCity_id());
                preparedStatement.setString(14,keyMan.getArea_id());
                preparedStatement.setString(15,keyMan.getComp_lat());
                preparedStatement.setString(16,keyMan.getComp_lng());
                preparedStatement.setInt(17,0);
                preparedStatement.setString(18,"");
                preparedStatement.setString(19,currentTime);
                preparedStatement.setString(20, currentTime);
                preparedStatement.setString(21, "0");
                if (company_id == 0) {
                    preparedStatement.setString(22, "E");
                } else {
                    preparedStatement.setString(22, "y");
                }
                preparedStatement.setString(23, keyMan.getHuman_c_id());
                preparedStatement.setInt(24, 0);


                //sql = "insert into bs_addressbook(id,human_c_id,user_id,user_name,user_head,mobile,job_title,status,company_id,company_name,certi_status,industry_id,province_id,city_id,area_id,comp_lat,comp_lng,delete_flag,code,created_time,updated_time,is_vip,import_data) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";

            } else if (status == 2) {
                //update
                //sql = "update bs_addressbook set mobile = ? ,job_title = ?,company_name = ? where human_c_id = ? and user_name = ? and company_id = ?";
                sql = "update bs_addressbook set mobile = ? ,job_title = ?,company_name = ? where human_c_id = ? and user_name = ? and company_id = ?";
                preparedStatement = this.connection.prepareStatement(sql);
                preparedStatement.setString(1,"");
                preparedStatement.setString(2,keyMan.getKeymanJob());
                preparedStatement.setString(3,keyMan.getCompany_name());
                preparedStatement.setString(4,keyMan.getHuman_c_id());
                preparedStatement.setString(5,keyMan.getKeymanName());
                preparedStatement.setLong(6,keyMan.getCompany_id());
            }
            try {
                preparedStatement.executeUpdate();
                System.out.println("插入MySQL数据,human_c_id:"+keyMan.getHuman_c_id()+" "+new Date().toLocaleString());
                logger.info("插入数据");
            }catch (Exception e) {
                logger.error(e.getMessage());
                if (!e.getMessage().contains("for key 'human_c_id'")) {
                    //SendMailUtil.send("zww@app-tech.com", "MySQL Exception Info", e.getMessage());
                }
            }

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
    }

}
