package com.app.data.clean.flink;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.PropertyNamingStrategy;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.app.data.clean.entity.CrawlerData;
import com.app.data.clean.service.*;
import jodd.util.StringUtil;
import org.apache.flink.streaming.api.functions.ProcessFunction;
import org.apache.flink.util.Collector;
import org.apache.flink.util.OutputTag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.UUID;

public class FlinkOutputMap extends ProcessFunction<String, Object> implements Serializable {

    private Logger logger = LoggerFactory.getLogger(FlinkOutputMap.class);

    private static final long serialVersionUID = 2186704587450967314L;

    private String targetTopic = "online_rtclean_data";


    private CleanShareholderinfoall cleanShareholderinfoall;

    private CleanFaithlessDetails cleanFaithlessDetails;

    private CleanPunishcreditchina cleanPunishcreditchina;

    private CleanBonds cleanBonds;

    private CleanLandPurchase cleanLandPurchase;

    private CleanImportandexports cleanImportandexports;

    private CleanCompanyAll cleanCompanyAll;

    private CleanAddress cleanAddress ;

    private CleanRegAddress cleanRegAddress ;

    private AddressBookInfo addressBookInfo ;

    private PromotionService promotionService ;



    public FlinkOutputMap() {
        cleanImportandexports = new CleanImportandexports();
        cleanCompanyAll = new CleanCompanyAll();
        cleanAddress = new CleanAddress();
        cleanLandPurchase = new CleanLandPurchase();
        cleanBonds = new CleanBonds();
        cleanPunishcreditchina = new CleanPunishcreditchina();
        cleanFaithlessDetails = new CleanFaithlessDetails();
        cleanShareholderinfoall = new CleanShareholderinfoall();
        cleanRegAddress = new CleanRegAddress();
        addressBookInfo = new AddressBookInfo();
        promotionService = new PromotionService();
    }


    public String getData(String value) throws Exception {
        JSONObject jsonObject = null;
        try {
            jsonObject = JSONObject.parseObject(value);
        } catch (Exception e) {
            logger.error("JSON数据格式错误,{}", e.getMessage());
            //SendMailUtil.send("zww@app-tech.com", "Clean Exception JSON数据格式错误", e.getMessage());
            throw e;
        }
        try {
            CrawlerData crawlerData = cleanId(jsonObject);

//      开始解析
//        jsonObject.put("grab_time", DateUtil.formatDate(new Date()));
            cleanShareholderinfoall.clean(jsonObject);
            cleanFaithlessDetails.clean(jsonObject);
            cleanPunishcreditchina.clean(jsonObject);
            cleanBonds.clean(jsonObject);
            cleanLandPurchase.clean(jsonObject);
            cleanImportandexports.clean(jsonObject);
            cleanCompanyAll.clean(jsonObject);
            cleanAddress.clean(jsonObject);
            cleanRegAddress.clean(jsonObject);
            //清洗完成标记时间
            crawlerData.getSign().setEnd_clean_time(DateTime.now());
            crawlerData.getSign().setClean_cost_time(DateUtil.between(crawlerData.getSign().getStart_clean_time(), crawlerData.getSign().getEnd_clean_time(), DateUnit.MS));
            jsonObject.put("sign", crawlerData.getSign());
            logger.info("数据数据时间{}毫秒", crawlerData.getSign().getClean_cost_time());
            jsonObject.put("grab_time", crawlerData.getGrabTime());
            //如果是企业并且不是个体户发起爬取任务
            sendDateAll(crawlerData);
            //判断是否为异地经营
            offSiteOperation(jsonObject);
            //通过下划线输出字段
            SerializeConfig config = new SerializeConfig();
            config.propertyNamingStrategy = PropertyNamingStrategy.SnakeCase;
            return JSONObject.toJSONString(jsonObject, config, SerializerFeature.DisableCircularReferenceDetect);
        } catch (Exception e) {
            logger.error("错误数据：", value);
            //SendMailUtil.send("zww@app-tech.com", "Clean Exception Info", e.getMessage()+"    Error MEssage:"+value);
            //如果出错从redis中删除id
            String id = jsonObject.getString("company_info_id");
            /*if (StringUtil.isNotBlank(id))
                jedis.del(id);*/
            throw e;
        }
    }

    private void offSiteOperation(JSONObject jsonObject) {
        //获取reg_address
        String company_all = jsonObject.getString("company_all");
        JSONObject company_all_json = JSONObject.parseObject(company_all);
        String address = company_all_json.getString("address");
        String reg_address = company_all_json.getString("reg_address");
        //判断数据是否为异地经营
        if (StringUtil.isNotBlank(reg_address) && !address.equals(reg_address)) {
            //把异地经营字段放进去,异地经营
            jsonObject.put("adr_not_reg_adr",1);
        } else {
            //把异地经营字段放进去,非异地经营
            jsonObject.put("adr_not_reg_adr",0);
        }
    }


    private CrawlerData cleanId(JSONObject jsonObject) throws Exception {
        try {
            CrawlerData crawlerData = jsonObject.toJavaObject(CrawlerData.class);
            //final String id = cleanLevelOne(crawlerData.getMd5Id(), crawlerData.getSign().getData_level());
            //crawlerData.setCompanyInfoId(id);
            //jsonObject.put("company_info_id", crawlerData.getCompanyInfoId());
            crawlerData.getSign().setStart_clean_time(DateTime.now());
            return crawlerData;
        } catch (LevelRuntimeException e) {
            //SendMailUtil.send("zww@app-tech.com", "Clean Exception 等级错误", e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("没有头文件错误", e);
            //SendMailUtil.send("zww@app-tech.com", "Clean Exception 没有头文件错误", e.getMessage());
            throw e;
        }
    }

    public String cleanLevelOne(String md5_id, Integer dataLevel) {
        final String rowkey = StringUtil.remove(UUID.randomUUID().toString(), "-").toLowerCase();
        return rowkey;
    }


    public void sendDateAll(CrawlerData crawlerData) {
        if (crawlerData.getSign().getData_level() > 1)
            return;
        if (StringUtil.isBlank(crawlerData.getCompanyAll().getCompany_type()))
            return;
        if (StringUtil.indexOfIgnoreCase(crawlerData.getCompanyAll().getCompany_type(), "个体") > 1)
            return;
        if (StringUtil.isBlank(crawlerData.getCompanyAll().getCompany_source_id()))
            return;
        //HttpRequest.post("http://crawler.app-tech.com/companyid").form("companyId", crawlerData.getCompanyAll().getCompany_source_id()).sendAsync();
    }



    //开启测流输出
    private static final OutputTag<String> online_error_data = new OutputTag<String>("online_error_data") {};
    private static final OutputTag<String> online_rtclean_data = new OutputTag<String>("online_rtclean_data") {};
//    private static final OutputTag<String> reg_address_mysql = new OutputTag<String>("reg_address_mysql") {};
//    private static final OutputTag<String> bs_addressbook = new OutputTag<String>("bs_addressbook") {};
    private static final OutputTag<String> cooperative = new OutputTag<String>("cooperative") {};

    public void processElement(String message, Context context, Collector<Object> collector) throws Exception {

        //处理数据
        String cleanDate = "";
        logger.info("{}-原数据:{}", targetTopic, message);
        JSONObject jsonObject = JSONObject.parseObject(message);

        if (null == jsonObject.getString("repeat_data")) {
            try {
//                System.out.println("source_date"+new Date().toLocaleString() +message);
                jsonObject.remove("repeat_data");

                String dealData = jsonObject.toJSONString();
                //推广信息
                if (jsonObject.getString("cooperative") != null) {
                    //处理合作信息的数据
                    String dealInfo = promotionService.dealPromotionInfo(dealData);
//                    System.out.println("success"+new Date().toLocaleString()+dealInfo);
                    //将单独的合作信息发送到新的侧流中
                    context.output(cooperative,dealInfo);
                    //将处理后数据放入正常的流中
                    cleanDate = dealInfo;
                    logger.info("合作信息数据:{}", cleanDate);
                } else {
                    cleanDate = getData(dealData);
                    //同时正确数据也放入侧流
                    context.output(online_rtclean_data,cleanDate);
                    logger.info("清洗数据完成数据:{}", cleanDate);
                }
//                System.out.println("success"+new Date().toLocaleString()+cleanDate);
                //正确数据进入普通输出流
                collector.collect(cleanDate);
                //注册信息进入MySQL侧流

                //JSONObject jsonObject1 = JSONObject.parseObject(cleanDate);
                //String reg_address_detail = jsonObject1.getString("reg_address_detail");
                /*if (StringUtil.isNotBlank(reg_address_detail)) {
                    //System.out.println("注册地址信息  "+reg_address_detail);
                    context.output(reg_address_mysql,cleanDate);
                }*/
                //进行通讯录的信息处理,存在通讯录唯一ID时候
                /*JSONObject dealData = JSONObject.parseObject(cleanDate);
                if (cleanDate.contains("shareholders") && !cleanDate.contains("keymans")) {
                    dealData.put("addressBook_type","onlyshareholders");
                } else if (cleanDate.contains("keymans") && !cleanDate.contains("shareholders")) {
                    dealData.put("addressBook_type","onlykeymans");
                } else if (cleanDate.contains("keymans") && cleanDate.contains("shareholders")) {
                    dealData.put("addressBook_type","bothall");
                } else {
                    dealData.put("addressBook_type","nothing");
                }
                //当没有主要人员和股东信息时不进行通讯录的侧流中
                String addressBook = dealData.toJSONString();
                if (!addressBook.contains("nothing")) {
                    //记录通讯录信息
                    String personInfo = addressBookInfo.getPersonInfo(addressBook);
                    context.output(bs_addressbook,personInfo);
                }*/

                logger.info("数据发出成功");

            } catch (LevelRuntimeException e) {
                logger.info("{}", e.getMessage());
                //System.out.println(e.getMessage());
            } catch (Exception e) {
                //更新  online_error_data
                //问题数据进入侧流输出
                context.output(online_error_data,message);
                logger.error("数据清洗错误~,错误数据：", e);
                //SendMailUtil.send("zww@app-tech.com", "Clean Exception 数据清洗错误", e.getMessage());
                //System.out.println("err"+message+" 时间:"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            }
        }

    }


}
