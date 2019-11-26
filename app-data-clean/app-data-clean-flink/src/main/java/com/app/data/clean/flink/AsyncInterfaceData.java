package com.app.data.clean.flink;

import com.alibaba.fastjson.JSONObject;
import com.app.data.clean.entity.CompanyAll;
import com.app.data.clean.entity.CrawlerData;
import com.app.data.clean.service.SendMailUtil;
import jodd.util.StringUtil;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.async.ResultFuture;
import org.apache.flink.streaming.api.functions.async.RichAsyncFunction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.io.Serializable;
import java.util.Collections;
import java.util.UUID;

//import org.apache.flink.streaming.api.functions.async.collector.AsyncCollector;

public class AsyncInterfaceData extends RichAsyncFunction<String, String> implements Serializable {


    private Logger logger = LoggerFactory.getLogger(AsyncInterfaceData.class);


    //private transient RedisClient client;

    public transient JedisPool jedisPool;
    public transient Jedis jedis;
    public String host = "192.168.20.140";


    @Override
    public void open(Configuration parameters) throws Exception {
        super.open(parameters);
        jedisPool = new JedisPool(new GenericObjectPoolConfig(),this.host,6379);
        jedis = jedisPool.getResource();
    }

    @Override
    public void close() throws Exception {
        //连接关闭
        //client.close();
        if (null != jedis) {
            jedis.close();
        }
        if (null != jedisPool) {
            jedisPool.destroy();
        }

    }

    @Override
    public void asyncInvoke(String message, ResultFuture<String> asyncCollector) throws Exception {
        try {
            logger.info("连接redis查询...");
            //message = "{\"address_detail\":{\"area_id\":\"420821\",\"city\":\"荆门市\",\"city_id\":\"420800\",\"comp_lat\":\"31.020754\",\"comp_lng\":\"113.133334\",\"country\":\"中国\",\"district\":\"京山县\",\"location\":\"113.133334,31.020754\",\"province\":\"湖北省\",\"province_id\":\"420000\",\"township\":\"[]\"},\"company_info_id\":\"82dc589c25fa4fc7b38b77be4156f547\",\"time_stamp\":\"1558501907.565957\",\"md5_id\":\"dea3239efcfae9b0c9dc2209a86e3035\",\"grab_time\":\"2019-05-22 13:11:45\",\"sign\":{\"agent_ip\":\"111.77.99.29:6888\",\"clean_cost_time\":10,\"crawler_id\":\"13175738438\",\"crawler_name\":\"app\",\"crawler_source\":\"tianyancha\",\"crawler_type\":\"天眼查爬虫全国及时爬数据\",\"crawler_version\":\"19.1.9\",\"crawler_web\":\"https://www.tianyancha.com/company/3288005931\",\"data_level\":3,\"end_clean_time\":1558501905978,\"host_ip\":\"192.168.5.238\",\"start_clean_time\":1558501905968,\"words_version\":\"1.0.0\"},\"company_all\":{\"company_source_id\":\"3288005931\",\"company_type\":\"有限责任公司分公司(自然人独资)\",\"industry_id\":\"109\",\"grab_update_time\":\"1557863664000\",\"business_term\":\"2018-12-27至无固定期限\",\"company_used_name\":\"-\",\"company_website\":\"暂无信息\",\"reg_address\":\"京山经济开发区轻机大道凯发清华园1幢1单元1层15号\",\"company_email\":\"暂无信息\",\"taxpayer_qualification\":\"-\",\"industry\":\"软件和信息技术服务业\",\"reg_capital_type\":\"人民币\",\"approved_date\":\"2018-12-27\",\"staff_size\":\"-\",\"organization_code\":\"MA497E1N3\",\"company_relation\":\"https://dis.tianyancha.com/dis/old#/show?ids=3288005931&_t=1558501896046&origin=https%3A%2F%2Fwww.tianyancha.com&mobile=13175738438&time=2019-05-22T05:11:35.193Zc-8&key=a0b8ccc99e4cce3046a9e053367e3f1e&cnz=true\",\"reg_organs\":\"京山市工商行政管理局\",\"contributors_in\":\"-\",\"contributed_capital\":\"-\",\"share_pic\":\"http://static.tianyancha.com/equity/daebbb90cd68331f390416a9d388152d.png\",\"intro\":\"暂无信息\",\"social_credit_code\":\"91420821MA497E1N3H\",\"now_get_lock_id\":\"01\",\"logo_image_src\":\"\",\"company_name_english\":\"-\",\"legal_person\":\"谭海涛\",\"address\":\"京山经济开发区轻机大道凯发清华园1幢1单元1层15号\",\"taxpayer_code\":\"91420821MA497E1N3H\",\"reg_capital_num\":\"-\",\"data_form\":\"NowGetDetail\",\"history\":\"https://dis.tianyancha.com/dis/timeline?graphId=3288005931&cnz=true\",\"company_tel\":\"暂无信息\",\"business_scope\":\"计算机软硬件、安防系统设备、电子产品、办公用品、智能家居技术开发、销售、安装、维修；楼宇智能化工程、装饰工程设计及施工；广告设计、制作、发布、代理；货物进出口、技术进出口（不含国家禁止或限制进出口的货物及技术）。（依法须经批准的项目，经相关部门批准后方可开展经营活动）\",\"reg_date\":\"2018-12-27\",\"registration_code\":\"-\",\"capital_type_id\":\"1000\",\"regist_status_id\":\"2\",\"reg_status\":\"存续\",\"company_name\":\"武汉嘉骐丰科技有限公司京山分公司\"},\"share_detail_dict\":{\"special\":\"\",\"data\":{\"path\":[],\"structure\":{\"sh_type\":\"-\",\"amount\":\"-\",\"parentName\":\"-\",\"regCapital\":\"0\",\"children\":[],\"actualHolding\":\"1\",\"name\":\"武汉嘉骐丰科技有限公司京山分公司\",\"id\":\"3288005931\",\"type\":\"C\",\"percent\":\"-\",\"cid\":0,\"info\":\"-\"}},\"vipMessage\":\"-\",\"state\":\"ok\"},\"_id\":\"174725527c5011e99353005056ad5710\"}";
            // 发出异步请求，返回结果的 Future
            //System.out.println("kafkaSend"+System.currentTimeMillis()+message);

            JSONObject jsonObject = JSONObject.parseObject(message);


            CrawlerData crawlerData = jsonObject.toJavaObject(CrawlerData.class);

            String crawler_source = crawlerData.getSign().getCrawler_source();

            //判断数据类型是否为合作信息
            if ("bdwork".equals(crawler_source)) {
                //合作信息的话把数据修改为等级2,让合作信息的数据可以进入清洗流
                crawlerData.getSign().setData_level(2);
            }


            final String rowkey = StringUtil.remove(UUID.randomUUID().toString(), "-").toLowerCase();
            //String md5Id = crawlerData.getMd5Id();
            String md5Id = jsonObject.getString("md5_id");
            //String md5Id = "820b42040a3280bc1bd9f525c4d58bb9";
            String id = jedis.get(md5Id);

//            System.out.println(id);

         /*String id = stringRedisTemplate.opsForValue().get(md5_id);
        if (StringUtil.isNotBlank(id)) {
            if (dataLevel == 1) {
//                throw new LevelRuntimeException("数据等级为1级的数据已经存在不需要清洗");
            }
        } else {
            stringRedisTemplate.opsForValue().setIfAbsent(md5_id, rowkey);
        }
        id = stringRedisTemplate.opsForValue().get(md5_id);*/

            if (StringUtil.isNotBlank(id)) {
                //String crawler_version = crawlerData.getSign().getCrawler_version();
                Integer data_level = crawlerData.getSign().getData_level();
                if (data_level == 1) {
                    jsonObject.put("repeat_data","1");
//                throw new LevelRuntimeException("数据等级为1级的数据已经存在不需要清洗");
                }
            } else {
                id = rowkey;
                jedis.set(md5Id,id);
            }
            //crawlerData.setCompanyInfoId(id);
            jsonObject.put("company_info_id",id);
            //String jsonString = JSONObject.toJSONString(crawlerData);




            //公司类型判断

            CompanyAll companyAll = crawlerData.getCompanyAll();

            if(null != companyAll) {
                String company_type = companyAll.getCompany_type();
                String company_type_id = "";

                //当公司类型出现错误的时候
                if (company_type == null || company_type.equals("") || company_type.equals("-")) {
                    company_type = "其他";
                    company_type_id = "204";
                } else {
                    //存在则从redis获取
                    String value = jedis.get(company_type);
                    //存在
                    if (value != null) {
                        String[] split = value.split("\\,");
                        company_type = split[0];
                        company_type_id = split[1];
                    } else {
                        StringBuilder stringBuilder = new StringBuilder();
                        jedis.set(company_type,stringBuilder.append("其他").append(",").append("204").toString());
                        company_type = "其他";
                        company_type_id = "204";
                    }
                }
                //替换原类型
                jsonObject.getJSONObject("company_all").replace("company_type",company_type);

                jsonObject.getJSONObject("company_all").put("company_type2_id",company_type_id);
            }


            //设置参数
 /*           companyAll.setCompany_type(company_type);
            companyAll.setCompany_type_id(company_type_id);*/

            //添加到jsonObject
//            jsonObject.remove("company_all");

//            String company_all = JSONObject.toJSONString(companyAll);



            asyncCollector.complete(Collections.singleton(jsonObject.toJSONString()));
            //asyncCollector.collect(Collections.singleton(jsonObject.toJSONString()));
        }catch (Exception e) {
            logger.error(e.getMessage());
            //SendMailUtil.send("zww@app-tech.com", "Redis Exception Info", e.getMessage());
        }



    }


    //当异步I / O请求超时时，默认情况下会引发异常并重新启动作业。如果要处理超时，可以覆盖该AsyncFunction#timeout方法
    /**
     * 公司类型判断
     *
     * CompanyAll companyAll = crawlerData.getCompanyAll();
     *
     * String company_type = companyAll.getCompany_type();
     * String company_type_id = "";
     *
     * //当公司类型出现错误的时候
     * if (company_type == null || company_type.equals("") || company_type.equals("-")) {
     *     company_type = "其他";
     *     company_type_id = "204";
     * } else {
     *     //存在则从redis获取
     *     String value = jedis.get(company_type);
     *     //存在
     *     if (value != null) {
     *         String[] split = value.split("\\,");
     *         company_type = split[0];
     *         company_type_id = split[1];
     *     } else {
     *         StringBuilder stringBuilder = new StringBuilder();
     *         jedis.set(company_type,stringBuilder.append("其他").append(",").append("204").toString());
     *         company_type = "其他";
     *         company_type_id = "204";
     *     }
     * }
     * //设置参数
     * companyAll.setCompany_type(company_type);
     * companyAll.setCompany_type_id(company_type_id);
     *
     * //添加到jsonObject
     * jsonObject.remove("company_all");
     *
     * String company_all = JSONObject.toJSONString(companyAll);
     *
     * jsonObject.put("company_all",company_all);
     */


}
