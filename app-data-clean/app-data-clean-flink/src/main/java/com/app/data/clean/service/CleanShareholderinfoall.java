package com.app.data.clean.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.app.data.clean.data.AbstractCleanData;
import jodd.util.StringUtil;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;

/**
 * "shareholderinfoall": [{
 * "reportYear": "2017",
 * "reportValue": [{
 * "shareholderinfobasic": {
 * "reportYear": "2017",
 * "reportSocialCreditCode": "91310117MA1J22HD7Y",
 * "reportWwwShop": "否",
 * "reportRegStatus": "开业",
 * "reportCompanyTel": "021-57710988",
 * "postalCode": "201612",
 * "reportAddress": "上海市漕河泾开发区松江高科技园区莘砖公路668号1101室-C",
 * "reportPeopleNums": "企业选择不公示",
 * "reportCompanyEmail": "无",
 * "reportCompanyName": "上海虎宝网络科技有限公司",
 * "investmentInf": "无"
 * }*                                        },
 * {
 * "shareholderinfoshare": [{
 * "shareDate": "2037-03-30",
 * "shareholder": "翁杰辉",
 * "shareRealWay": "货币",
 * "shareRealDate": "2017-04-14",
 * "shareWay": "货币",
 * "shareRealAmount": "2.5万元",
 * "shareAmount": "2.5万元",
 * "reportShar                            "2017"
 * },
 * {
 * "shareDate": "2037-03-30",
 * "shareholder": "杨青华",
 * "shareRealWay": "货币",
 * "shareRealDate": "2017-04-14",
 * "shareWay": "货币",
 * "shareRealAmount": "7.5万元",
 * "shareAmount": "7.5万元",
 * "reportShar                            "2017"
 * },
 * {
 * "shareDate": "2037-03-30",
 * "shareholder": "上海玉木投资管理事务所",
 * "shareRealWay": "货币",
 * "shareRealDate": "2017-05-23",
 * "shareWay": "货币",
 * "shareRealAmount": "12.5万元",
 * "shareAmount": "12.5万元",
 * "reportShar                            "2017"
 * },
 * {
 * "shareDate": "2037-03-30",
 * "shareholder": "孙文燕",
 * "shareRealWay": "货币",
 * "shareRealDate": "2017-04-15",
 * "shareWay": "货币",
 * "shareRealAmount": "27.5万元",
 * "shareAmount": "27.5万元",
 * "reportShar                            "2017"
 * }
 * ]
 * },
 * {
 * "shareholderinfoasset": {
 * "totalTax": "企业选择不公示",
 * "totalAsset": "企业选择不公示",
 * "totalEquity": "企业选择不公示",
 * "totalProfit": "企业选择不公示",
 * "totalDebt": "企业选择不公示",
 * "totalYear": "2017",
 * "totalSale": "企业选择不公示",
 * "businessIncome": "企业选择不公示",
 * "netProfit": "企业选择不公示"
 * }
 * }
 * ]
 * },
 * {
 * "reportYear": "2018",
 * "reportValue": [{
 * "shareholderinfobasic": {
 * "reportYear": "2018",
 * "reportSocialCreditCode": "91310117MA1J22HD7Y",
 * "reportWwwShop": "是",
 * "reportRegStatus": "开业",
 * "reportCompanyTel": "021-57710988",
 * "postalCode": "201612",
 * "reportAddress": "上海漕河泾开发区松江高科技园莘砖公路668号1101室 -C",
 * "reportPeopleNums": "企业选择不公示",
 * "reportCompanyEmail": "无",
 * "reportCompanyName": "上海虎宝网络科技有限公司",
 * "investmen                        "有"
 * }
 * },
 * {
 * "shareholderinfoint": [{
 * "wwwShopAddress": "http://www.app2017.com/",
 * "wwwYear": "2018",
 * "wwwShopName": "上海虎宝网络科技有限公司",
 * "wwwShopTy                            "
 * }                            ,
 * {
 * "wwwShopAddress": "http://www.app-tech.com/",
 * "wwwYear": "2018",
 * "wwwShopName": "上海虎宝网络科技有限公司",
 * "w                            pe": "网页"
 * },
 * {
 * "wwwShopAddress": "http://www.appred.com/",
 * "wwwYear": "2018",
 * "wwwShopName": "虎宝企业区域联盟",
 * "w                            pe": "网页"
 * }
 * ]
 * },
 * {
 * "shareholderinfoshare": [{
 * "shareDate": "2037-03-30",
 * "shareholder": "王伟泳",
 * "shareRealWay": "货币",
 * "shareRealDate": "2018-06-22",
 * "shareWay": "货币",
 * "shareRealAmount": "100万元",
 * "shareAmount": "100万元",
 * "reportS                            ": "2018"
 * },
 * {
 * "shareDate": "2037-03-30",
 * "shareholder": "杨青华",
 * "shareRealWay": "货币",
 * "shareRealDate": "2018-09-04",
 * "shareWay": "货币",
 * "shareRealAmount": "100.5万元",
 * "shareAmount": "100.5万元",
 * "reportS                            ": "2018"
 * },
 * {
 * "shareDate": "2037-03-30",
 * "shareholder": "王语歆",
 * "shareRealWay": "货币",
 * "shareRealDate": "2018-07-05",
 * "shareWay": "货币",
 * "shareRealAmount": "416.7万元",
 * "shareAmount": "416.7万元",
 * "reportS                            ": "2018"
 * },
 * {
 * "shareDate": "2037-03-30",
 * "shareholder": "上海玉木投资管理事务所",
 * "shareRealWay": "货币",
 * "shareRealDate": "2017-05-23",
 * "shareWay": "货币",
 * "shareRealAmount": "19.5万元",
 * "shareAmount": "19.5万元",
 * "reportS                            ": "2018"
 * },
 * {
 * "shareDate": "2037-03-30",
 * "shareholder": "翁杰辉",
 * "shareRealWay": "货币",
 * "shareRealDate": "2018-06-19",
 * "shareWay": "货币",
 * "shareRealAmount": "23.3万元",
 * "shareAmount": "23.3万元",
 * "reportS                            ": "2018"
 * },
 * {
 * "shareDate": "2037-03-30",
 * "shareholder": "盛复实业（上海）有限公司",
 * "shareRealWay": "货币",
 * "shareRealDate": "2018-03-30",
 * "shareWay": "货币",
 * "shareRealAmount": "50万元",
 * "shareAmount": "50万元",
 * "reportS                            ": "2018"
 * },
 * {
 * "shareDate": "2037-03-30",
 * "shareholder": "陈静",
 * "shareRealWay": "货币",
 * "shareRealDate": "2018-06-25",
 * "shareWay": "货币",
 * "shareRealAmount": "40万元",
 * "shareAmount": "40万元",
 * "reportS                            ": "2018"
 * },
 * {
 * "shareDate": "2037-03-30",
 * "shareholder": "孙文燕",
 * "shareRealWay": "货币",
 * "shareRealDate": "2018-06-13",
 * "shareWay": "货币",
 * "shareRealAmount": "50万元",
 * "shareAmount": "50万元",
 * "reportS                            ": "2018"
 * }
 * ]
 * },
 * {
 * "shareholderinfoasset": {
 * "totalTax": "企业选择不公示",
 * "totalAsset": "企业选择不公示",
 * "totalEquity": "企业选择不公示",
 * "totalProfit": "企业选择不公示",
 * "totalDebt": "企业选择不公示",
 * "totalYear": "2018",
 * "totalSale": "企业选择不公示",
 * "businessIncome": "企业选择不公示",
 * "netP                         "企业选择不公示"
 * }
 * },
 * {
 * "shareholderinfoinvest": [{
 * "investSocialCreditCode": "-",
 * "investCompanyName": "上海信号弹数据技术有限公司",
 * "detailre                        r": "2018"
 * }]
 * },
 * {
 * "shareholderinfochange": [{
 * "shareChangeDate": "2018-04-09",
 * "shareholderChangesYear": "2018",
 * "detailreportShareholder": "复盛实业（上海）有限公司",
 * "shareChangeBeforeRate": "0%",
 * "shareChangeAf                            : "6.25%"
 * },
 * {
 * "shareChangeDate": "2018-04-09",
 * "shareholderChangesYear": "2018",
 * "detailreportShareholder": "孙文燕",
 * "shareChangeBeforeRate": "55%",
 * "shareChangeAf                            : "6.25%"
 * },
 * {
 * "shareChangeDate": "2018-04-09",
 * "shareholderChangesYear": "2018",
 * "detailreportShareholder": "翁杰辉",
 * "shareChangeBeforeRate": "5%",
 * "shareChangeAfte                            "2.9125%"
 * },
 * {
 * "shareChangeDate": "2018-04-09",
 * "shareholderChangesYear": "2018",
 * "detailreportShareholder": "王伟泳",
 * "shareChangeBeforeRate": "0%",
 * "shareChangeAf                            : "12.5%"
 * },
 * {
 * "shareChangeDate": "2018-04-09",
 * "shareholderChangesYear": "2018",
 * "detailreportShareholder": "王语歆",
 * "shareChangeBeforeRate": "0%",
 * "shareChangeAfter                            52.0875%"
 * }
 * ]
 * }
 * ]
 * }
 * ],
 */
public class CleanShareholderinfoall extends AbstractCleanData implements Serializable {


    @Override
    public String getKey() {
        return "shareholderinfoall";
    }

    @Override
    public JSONObject cleanData(JSONObject jsonObject) throws Exception {
        return null;
    }

    @Override
    public JSONArray cleanArray(JSONArray jsonArray) throws Exception {
        if (CollectionUtils.isEmpty(jsonArray))
            return null;
        //根据最后一条信息更新企业联系信息
        JSONObject last = jsonArray.getJSONObject(jsonArray.size() - 1);
        JSONArray reportValue = last.getJSONArray("report_value");
        //抽取年报最后一条记录信息
        for (int i = 0; i < reportValue.size(); i++) {
            JSONObject obj = reportValue.getJSONObject(i);
            if (obj.containsKey("shareholderinfobasic")) {
                JSONObject shareholderinfobasic = obj.getJSONObject("shareholderinfobasic");
                String reportAddress = shareholderinfobasic.getString("report_address");
                String reportCompanyTel = shareholderinfobasic.getString("report_company_tel");

                setCompanyAll(reportAddress, reportCompanyTel);
                break;
            }
        }
        return null;
    }

    private void setCompanyAll(String reportAddress, String reportCompanyTel) {
        JSONObject company_all = this.source.getJSONObject("company_all");
        if (StringUtil.isNotBlank(reportAddress)) {
            if (reportAddress.length() > 5)
                company_all.put("address", reportAddress);
        }
        if (StringUtil.isNotBlank(reportCompanyTel)) {
            JSONArray companyTel = new JSONArray();
            if (company_all.containsKey("companyTel")) {
                companyTel = company_all.getJSONArray("companyTel");
            }
            boolean has = false;
            for (int i = 0; i < companyTel.size(); i++) {
                String tel = companyTel.getString(i);
                if (StringUtil.equals(tel, reportCompanyTel)) {
                    has = true;
                }
            }
            if (!has) {
                if (reportCompanyTel.length() > 5)
                    companyTel.add(reportCompanyTel);
            }
            company_all.put("companyTel", companyTel);
        }
    }
}
