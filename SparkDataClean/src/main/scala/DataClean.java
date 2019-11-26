import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import jodd.http.HttpRequest;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DataClean {

    public static void main(String[] args) {
        String a="{\"md5_id\":\"00fa3fa8fb781894fc30406fdcab8c86\",\"sign\":{},\"company_all\":{\"company_type\":\"集体所有制\",\"legal_person\":\"刘宏亮\",\"address\":\"永登县中堡火车站\",\"reg_capital_num\":\"300\",\"company_website\":\"\",\"company_email\":\"1906940943@qq.com\\t;\\t1906940943@pp.com\\t;\\t\",\"industry\":\"\",\"reg_capital_type\":\"人民币\",\"company_tel\":\"0931-6422244\",\"business_scope\":\"石灰石、石灰石粉、石英石、石膏、聚乙稀醇、氧化钙粉、氢氧钙粉的销售（依法须经批准的项目，经相关部门批准后方可开展经营活动）*\",\"reg_date\":\"1984-07-16\",\"reg_status\":\"存续\",\"company_name\":\"永登县坪城乡联营采石厂\",\"social_credit_code\":\"916201212245822302\"},\"_id\":\"019571b3f8b44add8eb249a3f83e2864\"}";
        String data = getData2(a);
        System.out.println(data);
    }

    public static String getData2(String value){
        String data=null;
        try{
            data = getData(value);
        }catch (Exception e){
            System.out.println("数据清洗错误~"+value);
        }
       return data;
    }

    public static String getData(String value){
        JSONObject jsonObject =null;
        try{
            jsonObject = JSONObject.parseObject(value);
        }catch (Exception e){
            System.out.println("数据格式错误："+"\n"+e);
        }
//      开始解析

        JSONObject sign = JSONObject.parseObject(jsonObject.getString("sign"));
        sign.put("big_data","1.0.0");
        jsonObject.put("sign",sign);

        String oldId = jsonObject.getString("_id");
        jsonObject.put("company_info_id",oldId);
        jsonObject.put("_id",null);

        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(date);
        jsonObject.put("grab_time",dateString);

        if(jsonObject.containsKey("faithless_details")){
            //faithless_details
            JSONArray faithless_details = jsonObject.getJSONArray("faithless_details");
            JSONArray faithless_detailsValue=new JSONArray();
            for(Object fd:faithless_details){
                JSONObject fdj = JSONObject.parseObject(fd.toString());
                JSONObject newfdj=new JSONObject();

                //iname faithless_name
                String iname = fdj.getString("iname");
                newfdj.put("faithless_name",iname);

                //businessentity faithless_legal_person
                String businessentity = fdj.getString("businessentity");
                newfdj.put("faithless_legal_person",businessentity);

                //gistid gist_id
                String gistid = fdj.getString("gistid");
                newfdj.put("gist_id",gistid);

                //areaname area_name
                String areaname = fdj.getString("areaname");
                newfdj.put("area_name",areaname);

                //cardnum card_num
                String cardnum = fdj.getString("cardnum");
                newfdj.put("card_num",cardnum);

                //courtname court_name
                String courtname = fdj.getString("courtname");
                newfdj.put("court_name",courtname);

                //publishdate faithless_publish_date
                String publishdate = fdj.getString("publishdate");
                newfdj.put("faithless_publish_date",getTime(publishdate));

                //type faithless_type
                String type = fdj.getString("type");
                newfdj.put("faithless_type",type);

                //gistunit gist_unit
                String gistunit = fdj.getString("gistunit");
                newfdj.put("gist_unit",gistunit);

                //duty faithless_duty
                String duty = fdj.getString("duty");
                newfdj.put("faithless_duty",duty);

                //performance faithless_status
                String performance = fdj.getString("performance");
                newfdj.put("faithless_status",performance);

                //regdate faithless_reg_date
                String regdate = fdj.getString("regdate");
                newfdj.put("faithless_reg_date",getTime(regdate));


                //casecode faithless_case_code
                String casecode = fdj.getString("casecode");
                newfdj.put("faithless_case_code",casecode);

                //disrupttypename disrupt_type_name
                String disrupttypename = fdj.getString("disrupttypename");
                newfdj.put("disrupt_type_name",disrupttypename);

                faithless_detailsValue.add(newfdj);
            }
            jsonObject.put("faithless_details",faithless_detailsValue);
        }



        if(jsonObject.containsKey("punishcreditchina")){
            //punishcreditchina
            JSONArray punishcreditchina = jsonObject.getJSONArray("punishcreditchina");
            JSONArray punishcreditchinaValue=new JSONArray();
            for(Object pd:punishcreditchina){
                JSONObject pdj = JSONObject.parseObject(pd.toString());
                JSONObject newpdj=new JSONObject();

                //result pun_credit_result
                String result = pdj.getString("result");
                newpdj.put("pun_credit_result",result);

                //areaName pun_credit_area
                String areaName = pdj.getString("areaName");
                newpdj.put("pun_credit_area",areaName);

                //typeSecond pun_credit_type2
                String typeSecond = pdj.getString("typeSecond");
                newpdj.put("pun_credit_type2",typeSecond);

                //reason pun_credit_reason
                String reason = pdj.getString("reason");
                newpdj.put("pun_credit_reason",reason);

                //punishNumber pun_credit_code
                String punishNumber = pdj.getString("punishNumber");
                newpdj.put("pun_credit_code",punishNumber);

                //evidence pun_credit_evidence
                String evidence = pdj.getString("evidence");
                newpdj.put("pun_credit_evidence",evidence);

                //punishStatus pun_credit_status
                String punishStatus = pdj.getString("punishStatus");
                newpdj.put("pun_credit_status",punishStatus);

                //decisionDate pun_credit_date
                String decisionDate = pdj.getString("decisionDate");
                newpdj.put("pun_credit_date",decisionDate);

                //type pun_credit_type
                String type = pdj.getString("type");
                newpdj.put("pun_credit_type",type);

                //departmentName pun_credit_operater
                String departmentName = pdj.getString("departmentName");
                newpdj.put("pun_credit_operater",departmentName);

                //punishName
                String punishName = pdj.getString("punishName");
                newpdj.put("pun_credit_name",punishName);

                punishcreditchinaValue.add(newpdj);
            }
            jsonObject.put("punishcreditchina",punishcreditchinaValue);
        }


        if(jsonObject.containsKey("bonds")){
            //bonds--->bond_details
            JSONArray bonds = jsonObject.getJSONArray("bonds");
            JSONArray bondsValue=new JSONArray();
            for(Object bd:bonds){
                JSONObject bds = JSONObject.parseObject(bd.toString());
                JSONObject bdsj = JSONObject.parseObject(bds.getString("bond_details"));
                JSONObject newbdsj=new JSONObject();

                //id bond_id
                String id = bdsj.getString("id");
                newbdsj.put("bond_id",id);

                //bondName bond_name
                String bondName = bdsj.getString("bondName");
                newbdsj.put("bond_name",bondName);

                //bondNum bond_num
                String bondNum = bdsj.getString("bondNum");
                newbdsj.put("bond_num",bondNum);

                //publisherName bond_publisher_name
                String publisherName = bdsj.getString("publisherName");
                newbdsj.put("bond_publisher_name",publisherName);

                //bondType bond_type
                String bondType = bdsj.getString("bondType");
                newbdsj.put("bond_type",bondType);

                //publishTime bond_publish_time
                String publishTime = bdsj.getString("publishTime");
                newbdsj.put("bond_publish_time",getTime(publishTime));

                //publishExpireTime bond_publish_expire_time
                String publishExpireTime = bdsj.getString("publishExpireTime");
                newbdsj.put("bond_publish_expire_time",getTime(publishExpireTime));

                //bondTimeLimit bond_time_limit
                String bondTimeLimit = bdsj.getString("bondTimeLimit");
                newbdsj.put("bond_time_limit",bondTimeLimit);

                //bondTradeTime bond_trade_time
                String bondTradeTime = bdsj.getString("bondTradeTime");
                newbdsj.put("bond_trade_time",getTime(bondTradeTime));

                //calInterestType bond_cal_interest_type
                String calInterestType = bdsj.getString("calInterestType");
                newbdsj.put("bond_cal_interest_type",calInterestType);

                //bondStopTime bond_stop_time
                String bondStopTime = bdsj.getString("bondStopTime");
                newbdsj.put("bond_stop_time",getTime(bondStopTime));

                //creditRatingGov bond_credit_rating_gov
                String creditRatingGov = bdsj.getString("creditRatingGov");
                newbdsj.put("bond_credit_rating_gov",creditRatingGov);

                //debtRating bond_debt_rating
                String debtRating = bdsj.getString("debtRating");
                newbdsj.put("bond_debt_rating",debtRating);

                //faceValue bond_face_value
                String faceValue = bdsj.getString("faceValue");
                newbdsj.put("bond_face_value",faceValue);

                //refInterestRate bond_ref_interest_rate
                String refInterestRate = bdsj.getString("refInterestRate");
                newbdsj.put("bond_ref_interest_rate",refInterestRate);

                //faceInterestRate bond_face_interest_rate
                String faceInterestRate = bdsj.getString("faceInterestRate");
                newbdsj.put("bond_face_interest_rate",faceInterestRate);

                //realIssuedQuantity bond_real_issued_quantity
                String realIssuedQuantity = bdsj.getString("realIssuedQuantity");
                newbdsj.put("bond_real_issued_quantity",realIssuedQuantity);

                //planIssuedQuantity bond_plan_issued_quantity
                String planIssuedQuantity = bdsj.getString("planIssuedQuantity");
                newbdsj.put("bond_plan_issued_quantity",planIssuedQuantity);

                //issuedPrice bond_issued_price
                String issuedPrice = bdsj.getString("issuedPrice");
                newbdsj.put("bond_issued_price",issuedPrice);

                //interestDiff bond_interest_diff
                String interestDiff = bdsj.getString("interestDiff");
                newbdsj.put("bond_interest_diff",interestDiff);

                //payInterestHZ bond_pay_interest_hz
                String payInterestHZ = bdsj.getString("payInterestHZ");
                newbdsj.put("bond_pay_interest_hz",payInterestHZ);

                //startCalInterestTime bond_startd_interest_time
                String startCalInterestTime = bdsj.getString("startCalInterestTime");
                newbdsj.put("bond_startd_interest_time",getTime(startCalInterestTime));

                //exeRightType bond_exe_right_type
                String exeRightType = bdsj.getString("exeRightType");
                newbdsj.put("bond_exe_right_type",exeRightType);

                //exeRightTime bond_exe_right_time
                String exeRightTime = bdsj.getString("exeRightTime");
                newbdsj.put("bond_exe_right_time",getTime(exeRightTime));

                //escrowAgent bond_escrow_agent
                String escrowAgent = bdsj.getString("escrowAgent");
                newbdsj.put("bond_escrow_agent",escrowAgent);

                //flowRange bond_flow_range
                String flowRange = bdsj.getString("flowRange");
                newbdsj.put("bond_flow_range",flowRange);

                //remark bond_remark bond_remark
                String remark = bdsj.getString("remark");
                newbdsj.put("bond_remark",remark);

                //tip bond_tip
                String tip = bdsj.getString("tip");
                newbdsj.put("bond_tip",tip);

                //createTime bond_create_time
                String createTime = bdsj.getString("createTime");
                newbdsj.put("bond_create_time",getTime(createTime));

                //updateTime bond_update_time
                String updateTime = bdsj.getString("updateTime");
                newbdsj.put("bond_update_time",getTime(updateTime));

                //isDelete bond_is_delete
                String isDelete = bdsj.getString("isDelete");
                newbdsj.put("bond_is_delete",isDelete);

                bds.put("bond_details",newbdsj);
                bondsValue.add(bds);
            }
            jsonObject.put("bonds",bondsValue);
        }

        if(jsonObject.containsKey("land_purchase")){
            //land_purchase--->buy_land_details
            JSONArray land_purchase = jsonObject.getJSONArray("land_purchase");
            JSONArray land_purchaseValue=new JSONArray();
            for(Object lp:land_purchase){
                JSONObject lps = JSONObject.parseObject(lp.toString());
                JSONObject lpsj = JSONObject.parseObject(lps.getString("buy_land_details"));
                JSONObject newlpsj=new JSONObject();

                //id land_id
                String id = lpsj.getString("id");
                newlpsj.put("land_id",id);

                //adminRegion land_admin_region
                String adminRegion = lpsj.getString("adminRegion");
                newlpsj.put("land_admin_region",adminRegion);

                //elecSupervisorNo land_elec_supervisor_no
                String elecSupervisorNo = lpsj.getString("elecSupervisorNo");
                newlpsj.put("land_elec_supervisor_no",elecSupervisorNo);

                //signedDate land_signed_date
                String signedDate = lpsj.getString("signedDate");
                newlpsj.put("land_signed_date",signedDate);

                //totalArea land_total_area
                String totalArea = lpsj.getString("totalArea");
                newlpsj.put("land_total_area",totalArea);

                //location land_location
                String location = lpsj.getString("location");
                newlpsj.put("land_location",location);

                //assignee land_assignee
                String assignee = lpsj.getString("assignee");
                newlpsj.put("land_assignee",assignee);

                //parentCompany land_parent_company
                String parentCompany = lpsj.getString("parentCompany");
                newlpsj.put("land_parent_company",parentCompany);

                //purpose land_purpose
                String purpose = lpsj.getString("purpose");
                newlpsj.put("land_purpose",purpose);

                //supplyWay land_supply_way
                String supplyWay = lpsj.getString("supplyWay");
                newlpsj.put("land_supply_way",supplyWay);

                //minVolume land_min_volume
                String minVolume = lpsj.getString("minVolume");
                newlpsj.put("land_min_volume",minVolume);

                //maxVolume land_max_volume
                String maxVolume = lpsj.getString("maxVolume");
                newlpsj.put("land_max_volume",maxVolume);

                //dealPrice land_deal_price
                String dealPrice = lpsj.getString("dealPrice");
                newlpsj.put("land_deal_price",dealPrice);

                //startTime land_start_time
                String startTime = lpsj.getString("startTime");
                newlpsj.put("land_start_time",startTime);

                //linkUrl land_link_url
                String linkUrl = lpsj.getString("linkUrl");
                newlpsj.put("land_link_url",linkUrl);

                //endTime land_end_time
                String endTime = lpsj.getString("endTime");
                newlpsj.put("land_end_time",endTime);

                //createTime land_create_time
                String createTime = lpsj.getString("createTime");
                newlpsj.put("land_create_time",createTime);

                //updateTime land_update_time
                String updateTime = lpsj.getString("updateTime");
                newlpsj.put("land_update_time",updateTime);

                lps.put("buy_land_details",newlpsj);
                land_purchaseValue.add(lps);
            }
            jsonObject.put("land_purchase",land_purchaseValue);
        }


        if(jsonObject.containsKey("importandexports")){
            JSONArray importandexports = jsonObject.getJSONArray("importandexports");
            JSONArray importandexportsValue=new JSONArray();
            for(Object iae:importandexports){
                JSONObject iaej = JSONObject.parseObject(iae.toString());
                JSONObject credit_business_datails = JSONObject.parseObject(iaej.getString("credit_business_datails"));

                String sanction = credit_business_datails.getString("sanction");
                iaej.put("sanction",sanction);

                JSONArray cra = JSONArray.parseArray(credit_business_datails.getString("creditRating"));
                for(Object x:cra){
                    JSONObject x1 = JSONObject.parseObject(x.toString());

                    //creditRating credit_rating
                    String creditRating = x1.getString("creditRating");
                    iaej.put("credit_rating",creditRating);

                    //authenticationCode
                    String authenticationCode = x1.getString("authenticationCode");
                    iaej.put("credit_auther_code",authenticationCode);
                }

                String baseInfo = credit_business_datails.getString("baseInfo");
                JSONObject bij=JSONObject.parseObject(baseInfo);

                JSONObject j3=new JSONObject();

                //industryCategory industry_category
                String industryCategory = bij.getString("industryCategory");
                iaej.put("industry_category",industryCategory);

                //validityDate credit_validity_date
                String validityDate = bij.getString("validityDate");
                iaej.put("credit_validity_date",validityDate);

                //annualReport credit_annual_report
                String annualReport = bij.getString("annualReport");
                iaej.put("credit_annual_report",annualReport);

                //economicDivision economic_division
                String economicDivision = bij.getString("economicDivision");
                iaej.put("economic_division",economicDivision);

                //status credit_status
                String status = bij.getString("status");
                iaej.put("credit_status",status);

                //recordDate credit_record_date
                String recordDate = bij.getString("recordDate");
                iaej.put("credit_record_date",recordDate);

                //managementCategory management_category
                String managementCategory = bij.getString("managementCategory");
                iaej.put("management_category",managementCategory);

                //administrativeDivision administrative_division
                String administrativeDivision = bij.getString("administrativeDivision");
                iaej.put("administrative_division",administrativeDivision);

                //crCode cr_code
                String crCode = bij.getString("crCode");
                iaej.put("cr_code",crCode);

                //specialTradeArea special_trade_area
                String specialTradeArea = bij.getString("specialTradeArea");
                iaej.put("special_trade_area",specialTradeArea);

                //customsRegisteredAddress customs_registered_address
                String customsRegisteredAddress = bij.getString("customsRegisteredAddress");
                iaej.put("customs_registered_address",customsRegisteredAddress);

                //types trade_types
                String types = bij.getString("types");
                iaej.put("trade_types",types);

                iaej.put("credit_business_datails",null);
                importandexportsValue.add(iaej);
            }
            jsonObject.put("importandexports",importandexportsValue);
        }


        if(jsonObject.containsKey("company_all")){
            String company_all = jsonObject.getString("company_all");
            JSONObject caj = JSONObject.parseObject(company_all);
            if(caj.containsKey("company_used_name")){
                String company_used_name = caj.getString("company_used_name");
                if(company_used_name.equals("")){
                    caj.put("company_used_name","");
                }else {
                    if(company_used_name.equals("暂无信息")){
                        caj.put("company_used_name","");
                    }else {
                        JSONArray jsonArray = JSONArray.parseArray(company_used_name);
                        String sb="";
                        for(Object o:jsonArray){
                            if(sb==""){
                                sb=o.toString();
                            }else {
                                sb=sb+","+o.toString();
                            }
                        }
                        caj.put("company_used_name",sb);
                    }
                }
            }

            if(caj.containsKey("company_email")){
                String company_email = caj.getString("company_email");
                if(company_email.equals("")){
                    caj.put("company_email","");
                }else {
                    if(company_email.equals("暂无信息")){
                        caj.put("company_email","");
                    }else if(company_email.equals(",")){
                        JSONArray jsonArray = JSONArray.parseArray(company_email);
                        String sx="";
                        for(Object o:jsonArray){
                            if(sx==""){
                                sx=o.toString();
                            }else {
                                sx=sx+","+o.toString();
                            }
                        }
                        caj.put("company_email",sx);
                    }else {
                        caj.put("company_email",company_email);
                    }
                }
            }

            if(caj.containsKey("company_tel")){
                String company_tel = caj.getString("company_tel");
                if(company_tel.equals("")){
                    caj.put("company_tel","");
                }else {
                    if(company_tel.equals("暂无信息")){
                        caj.put("company_tel","");
                    }else if(company_tel.equals(",")){
                        JSONArray jsonArray = JSONArray.parseArray(company_tel);
                        String sd="";
                        for(Object o:jsonArray){
                            if(sd==""){
                                sd=o.toString();
                            }else {
                                sd=sd+","+o.toString();
                            }
                        }
                        caj.put("company_tel",sd);
                    }else {
                        caj.put("company_tel",company_tel);
                    }
                }
            }
            jsonObject.put("company_all",caj);
        }

        JSONObject company_allj = JSONObject.parseObject(jsonObject.getString("company_all"));
        if(company_allj.containsKey("address") || company_allj.containsKey("reg_address")){
            String address = company_allj.getString("address");
            String regAddress = company_allj.getString("reg_address");
            try{
                if(address==null){
                    JSONObject addressDetail = GeoHttpApi.getAddressDetail("",regAddress);
                    jsonObject.put("address_detail",addressDetail);
                }else {
                    JSONObject addressDetail = GeoHttpApi.getAddressDetail("",address);
                    jsonObject.put("address_detail",addressDetail);
                }
            }catch (Exception e){
                System.out.println("地址获取失败");
            }
        }

        JSONObject address_detailj = JSONObject.parseObject(jsonObject.getString("address_detail"));
        String url = "http://platform.app-tech.com/api/app-platform-system/dict/getCompanyInfoIds";
        HttpRequest httpRequest =HttpRequest.get(url+"?reg_status="+company_allj.getString("reg_status")+"&industry="+company_allj.getString("industry")+"&reg_capital_type="+company_allj.getString("reg_capital_type")+"&area_id="+(jsonObject.containsKey("address_detail")?address_detailj.getString("area_id"):null));
        JSONObject zyhjson = (JSONObject.parseObject(JSON.parse(httpRequest.send().bodyBytes()).toString())).getJSONObject("result");
        company_allj.put("industry_id",zyhjson.getString("industry_id"));
        company_allj.put("capital_type_id",zyhjson.getString("capital_type_id"));
        company_allj.put("regist_status_id",zyhjson.getString("regist_status_id"));
        jsonObject.put("company_all",company_allj);

        if(jsonObject.containsKey("address_detail")){
            address_detailj.put("province_id",zyhjson.getString("province_id"));
            address_detailj.put("city_id",zyhjson.getString("city_id"));
            jsonObject.put("address_detail",address_detailj);
        }

        return jsonObject.toString();
    }

    public static String getTime(String time){
        Long l=null;
        try{
            l=Long.parseLong(time);
        }catch (Exception e){
            System.out.println("时间-->暂无信息");
        }
        if(l==null){
            return time;
        }else {
            SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
            String format = simpleDateFormat.format(l);
            return format;
        }
    }

}
