/**
 * Copyright 2019 bejson.com
 */
package com.app.data.clean.core.entity;

import cn.hutool.core.date.DateUtil;
import lombok.Data;

import java.util.List;

/**
 * Auto-generated: 2019-05-08 22:34:39
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
@Data
public class CrawlerData {

    private List<String> equitys;
    private Sign sign = new Sign();
    private List<String> faithlessDetails;
    private List<String> bonds;
    private List<String> courts;
    private String grabTime = DateUtil.now();
    private List<String> investEvents;
    private List<String> wechats;
    private List<String> courtAnnouncement;
    private List<String> importandexports;
    private AddressDetail addressDetail;
    private List<String> permissions;
    private String md5Id;
    private List<String> pledges;
    private List<String> tenderinfoalls;
    private List<Businesses> businesses;
    private List<String> lawsuitinfo;
    private List<String> taxcredits;
    private CompanyAll companyAll;
    private List<String> branchs;
    private List<String> owetax;
    private List<ProductVies> productVies;
    private List<String> landPurchase;
    private List<Icps> icps;
    private List<ShareholderInfoAll> shareholderInfoAll;
    private List<String> judicialAssistance;
    private List<Checks> checks;
    private List<String> forces;
    private String companyInfoId;
    private List<Changes> changes;
    private List<Keymans> keymans;
    private List<Keyteams> keyteams;
    private ShareDetailDict shareDetailDict;
    private List<Sharedtls> sharedtls;
    private List<String> qualitySpotChecks;
    private List<Invests> invests;
    private List<String> punishments;
    private List<String> lces;
    private List<String> foodChecks;
    private List<Keymandtls> keymandtls;
    private List<String> patents;
    private List<String> lawOperations;
    private List<String> abnormalOperations;
    private List<Copyrightworks> copyrightworks;
    private List<Shareholders> shareholders;
    private List<Copyrights> copyrights;
    private List<String> investees;
    private List<String> punishcreditchina;
    private List<Trademarks> trademarks;
    private List<String> judicialSales;
    private String timeStamp;
    private List<String> lawsuits;
    private List<String> productinfos;
    private List<String> tenders;
    private List<Recruits> recruits;


}