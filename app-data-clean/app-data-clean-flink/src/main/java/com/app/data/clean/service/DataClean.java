package com.app.data.clean.service;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.PropertyNamingStrategy;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.UUID;


public class DataClean {
   /* private final static Logger logger = LoggerFactory.getLogger(DataClean.class);
    @Autowired
    private CleanShareholderinfoall cleanShareholderinfoall;
    @Autowired
    private CleanFaithlessDetails cleanFaithlessDetails;
    @Autowired
    private CleanPunishcreditchina cleanPunishcreditchina;
    @Autowired
    private CleanBonds cleanBonds;
    @Autowired
    private CleanLandPurchase cleanLandPurchase;
    @Autowired
    private CleanImportandexports cleanImportandexports;
    @Autowired
    private CleanCompanyAll cleanCompanyAll;
    @Autowired
    private CleanAddress cleanAddress;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private CrawlerData cleanId(JSONObject jsonObject) throws Exception {
        try {
            CrawlerData crawlerData = jsonObject.toJavaObject(CrawlerData.class);
            final String id = this.cleanLevelOne(crawlerData.getMd5Id(), crawlerData.getSign().getData_level());
            crawlerData.setCompanyInfoId(id);
            jsonObject.put("company_info_id", id);
            crawlerData.getSign().setStart_clean_time(DateTime.now());
            return crawlerData;
        } catch (LevelRuntimeException e) {
            throw e;
        } catch (Exception e) {
            logger.error("没有头文件错误", e);
            throw e;
        }
    }

    public String cleanLevelOne(String md5_id, Integer dataLevel) {
        final String rowkey = StringUtil.remove(UUID.randomUUID().toString(), "-").toLowerCase();
        String id = stringRedisTemplate.opsForValue().get(md5_id);
        if (StringUtil.isNotBlank(id)) {
            if (dataLevel == 1) {
//                throw new LevelRuntimeException("数据等级为1级的数据已经存在不需要清洗");
            }
        } else {
            stringRedisTemplate.opsForValue().setIfAbsent(md5_id, rowkey);
        }
        id = stringRedisTemplate.opsForValue().get(md5_id);
        return id;
    }

    public String getData(String value) throws Exception {
        JSONObject jsonObject = null;
        try {
            jsonObject = JSONObject.parseObject(value);
        } catch (Exception e) {
            logger.error("JSON数据格式错误", e);
            throw e;
        }
        try {
            CrawlerData crawlerData = this.cleanId(jsonObject);
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
            //清洗完成标记时间
            crawlerData.getSign().setEnd_clean_time(DateTime.now());
            crawlerData.getSign().setClean_cost_time(DateUtil.between(crawlerData.getSign().getStart_clean_time(), crawlerData.getSign().getEnd_clean_time(), DateUnit.MS));
            jsonObject.put("sign", crawlerData.getSign());
            logger.info("数据数据时间{}毫秒", crawlerData.getSign().getClean_cost_time());
            jsonObject.put("grab_time", crawlerData.getGrabTime());
            //通过下划线输出字段
            SerializeConfig config = new SerializeConfig();
            config.propertyNamingStrategy = PropertyNamingStrategy.SnakeCase;
            return JSONObject.toJSONString(jsonObject, config, SerializerFeature.DisableCircularReferenceDetect);
        } catch (Exception e) {
            //如果出错从redis中删除id
            String id = jsonObject.getString("company_info_id");
            stringRedisTemplate.delete(id);
            throw e;
        }
    }*/


}
