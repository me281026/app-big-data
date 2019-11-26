/**
 * Copyright 2019 bejson.com
 */
package com.app.data.clean.entity;

import cn.hutool.core.date.DateUtil;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Auto-generated: 2019-05-08 22:34:39
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
@Data
public class CrawlerData implements Serializable {

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
    private AddressDetail regAddressDetail;
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

    private List<Cooperative> cooperative;

    public List<Cooperative> getCooperative() {
        return cooperative;
    }

    public void setCooperative(List<Cooperative> cooperative) {
        this.cooperative = cooperative;
    }

    public AddressDetail getRegAddressDetail() {
        return regAddressDetail;
    }

    public void setRegAddressDetail(AddressDetail regAddressDetail) {
        this.regAddressDetail = regAddressDetail;
    }

    public List<String> getEquitys() {
        return equitys;
    }

    public void setEquitys(List<String> equitys) {
        this.equitys = equitys;
    }

    public Sign getSign() {
        return sign;
    }

    public void setSign(Sign sign) {
        this.sign = sign;
    }

    public List<String> getFaithlessDetails() {
        return faithlessDetails;
    }

    public void setFaithlessDetails(List<String> faithlessDetails) {
        this.faithlessDetails = faithlessDetails;
    }

    public List<String> getBonds() {
        return bonds;
    }

    public void setBonds(List<String> bonds) {
        this.bonds = bonds;
    }

    public List<String> getCourts() {
        return courts;
    }

    public void setCourts(List<String> courts) {
        this.courts = courts;
    }

    public String getGrabTime() {
        return grabTime;
    }

    public void setGrabTime(String grabTime) {
        this.grabTime = grabTime;
    }

    public List<String> getInvestEvents() {
        return investEvents;
    }

    public void setInvestEvents(List<String> investEvents) {
        this.investEvents = investEvents;
    }

    public List<String> getWechats() {
        return wechats;
    }

    public void setWechats(List<String> wechats) {
        this.wechats = wechats;
    }

    public List<String> getCourtAnnouncement() {
        return courtAnnouncement;
    }

    public void setCourtAnnouncement(List<String> courtAnnouncement) {
        this.courtAnnouncement = courtAnnouncement;
    }

    public List<String> getImportandexports() {
        return importandexports;
    }

    public void setImportandexports(List<String> importandexports) {
        this.importandexports = importandexports;
    }

    public AddressDetail getAddressDetail() {
        return addressDetail;
    }

    public void setAddressDetail(AddressDetail addressDetail) {
        this.addressDetail = addressDetail;
    }

    public List<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<String> permissions) {
        this.permissions = permissions;
    }

    public String getMd5Id() {
        return md5Id;
    }

    public void setMd5Id(String md5Id) {
        this.md5Id = md5Id;
    }

    public List<String> getPledges() {
        return pledges;
    }

    public void setPledges(List<String> pledges) {
        this.pledges = pledges;
    }

    public List<String> getTenderinfoalls() {
        return tenderinfoalls;
    }

    public void setTenderinfoalls(List<String> tenderinfoalls) {
        this.tenderinfoalls = tenderinfoalls;
    }

    public List<Businesses> getBusinesses() {
        return businesses;
    }

    public void setBusinesses(List<Businesses> businesses) {
        this.businesses = businesses;
    }

    public List<String> getLawsuitinfo() {
        return lawsuitinfo;
    }

    public void setLawsuitinfo(List<String> lawsuitinfo) {
        this.lawsuitinfo = lawsuitinfo;
    }

    public List<String> getTaxcredits() {
        return taxcredits;
    }

    public void setTaxcredits(List<String> taxcredits) {
        this.taxcredits = taxcredits;
    }

    public CompanyAll getCompanyAll() {
        return companyAll;
    }

    public void setCompanyAll(CompanyAll companyAll) {
        this.companyAll = companyAll;
    }

    public List<String> getBranchs() {
        return branchs;
    }

    public void setBranchs(List<String> branchs) {
        this.branchs = branchs;
    }

    public List<String> getOwetax() {
        return owetax;
    }

    public void setOwetax(List<String> owetax) {
        this.owetax = owetax;
    }

    public List<ProductVies> getProductVies() {
        return productVies;
    }

    public void setProductVies(List<ProductVies> productVies) {
        this.productVies = productVies;
    }

    public List<String> getLandPurchase() {
        return landPurchase;
    }

    public void setLandPurchase(List<String> landPurchase) {
        this.landPurchase = landPurchase;
    }

    public List<Icps> getIcps() {
        return icps;
    }

    public void setIcps(List<Icps> icps) {
        this.icps = icps;
    }

    public List<ShareholderInfoAll> getShareholderInfoAll() {
        return shareholderInfoAll;
    }

    public void setShareholderInfoAll(List<ShareholderInfoAll> shareholderInfoAll) {
        this.shareholderInfoAll = shareholderInfoAll;
    }

    public List<String> getJudicialAssistance() {
        return judicialAssistance;
    }

    public void setJudicialAssistance(List<String> judicialAssistance) {
        this.judicialAssistance = judicialAssistance;
    }

    public List<Checks> getChecks() {
        return checks;
    }

    public void setChecks(List<Checks> checks) {
        this.checks = checks;
    }

    public List<String> getForces() {
        return forces;
    }

    public void setForces(List<String> forces) {
        this.forces = forces;
    }

    public String getCompanyInfoId() {
        return companyInfoId;
    }

    public void setCompanyInfoId(String companyInfoId) {
        this.companyInfoId = companyInfoId;
    }

    public List<Changes> getChanges() {
        return changes;
    }

    public void setChanges(List<Changes> changes) {
        this.changes = changes;
    }

    public List<Keymans> getKeymans() {
        return keymans;
    }

    public void setKeymans(List<Keymans> keymans) {
        this.keymans = keymans;
    }

    public List<Keyteams> getKeyteams() {
        return keyteams;
    }

    public void setKeyteams(List<Keyteams> keyteams) {
        this.keyteams = keyteams;
    }

    public ShareDetailDict getShareDetailDict() {
        return shareDetailDict;
    }

    public void setShareDetailDict(ShareDetailDict shareDetailDict) {
        this.shareDetailDict = shareDetailDict;
    }

    public List<Sharedtls> getSharedtls() {
        return sharedtls;
    }

    public void setSharedtls(List<Sharedtls> sharedtls) {
        this.sharedtls = sharedtls;
    }

    public List<String> getQualitySpotChecks() {
        return qualitySpotChecks;
    }

    public void setQualitySpotChecks(List<String> qualitySpotChecks) {
        this.qualitySpotChecks = qualitySpotChecks;
    }

    public List<Invests> getInvests() {
        return invests;
    }

    public void setInvests(List<Invests> invests) {
        this.invests = invests;
    }

    public List<String> getPunishments() {
        return punishments;
    }

    public void setPunishments(List<String> punishments) {
        this.punishments = punishments;
    }

    public List<String> getLces() {
        return lces;
    }

    public void setLces(List<String> lces) {
        this.lces = lces;
    }

    public List<String> getFoodChecks() {
        return foodChecks;
    }

    public void setFoodChecks(List<String> foodChecks) {
        this.foodChecks = foodChecks;
    }

    public List<Keymandtls> getKeymandtls() {
        return keymandtls;
    }

    public void setKeymandtls(List<Keymandtls> keymandtls) {
        this.keymandtls = keymandtls;
    }

    public List<String> getPatents() {
        return patents;
    }

    public void setPatents(List<String> patents) {
        this.patents = patents;
    }

    public List<String> getLawOperations() {
        return lawOperations;
    }

    public void setLawOperations(List<String> lawOperations) {
        this.lawOperations = lawOperations;
    }

    public List<String> getAbnormalOperations() {
        return abnormalOperations;
    }

    public void setAbnormalOperations(List<String> abnormalOperations) {
        this.abnormalOperations = abnormalOperations;
    }

    public List<Copyrightworks> getCopyrightworks() {
        return copyrightworks;
    }

    public void setCopyrightworks(List<Copyrightworks> copyrightworks) {
        this.copyrightworks = copyrightworks;
    }

    public List<Shareholders> getShareholders() {
        return shareholders;
    }

    public void setShareholders(List<Shareholders> shareholders) {
        this.shareholders = shareholders;
    }

    public List<Copyrights> getCopyrights() {
        return copyrights;
    }

    public void setCopyrights(List<Copyrights> copyrights) {
        this.copyrights = copyrights;
    }

    public List<String> getInvestees() {
        return investees;
    }

    public void setInvestees(List<String> investees) {
        this.investees = investees;
    }

    public List<String> getPunishcreditchina() {
        return punishcreditchina;
    }

    public void setPunishcreditchina(List<String> punishcreditchina) {
        this.punishcreditchina = punishcreditchina;
    }

    public List<Trademarks> getTrademarks() {
        return trademarks;
    }

    public void setTrademarks(List<Trademarks> trademarks) {
        this.trademarks = trademarks;
    }

    public List<String> getJudicialSales() {
        return judicialSales;
    }

    public void setJudicialSales(List<String> judicialSales) {
        this.judicialSales = judicialSales;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public List<String> getLawsuits() {
        return lawsuits;
    }

    public void setLawsuits(List<String> lawsuits) {
        this.lawsuits = lawsuits;
    }

    public List<String> getProductinfos() {
        return productinfos;
    }

    public void setProductinfos(List<String> productinfos) {
        this.productinfos = productinfos;
    }

    public List<String> getTenders() {
        return tenders;
    }

    public void setTenders(List<String> tenders) {
        this.tenders = tenders;
    }

    public List<Recruits> getRecruits() {
        return recruits;
    }

    public void setRecruits(List<Recruits> recruits) {
        this.recruits = recruits;
    }
}