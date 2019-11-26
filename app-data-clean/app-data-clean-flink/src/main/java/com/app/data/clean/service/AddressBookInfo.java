package com.app.data.clean.service;

import com.alibaba.fastjson.JSONObject;
import com.app.data.clean.entity.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AddressBookInfo implements Serializable {

    public String getPersonInfo(String message) {

        JSONObject jsonObject = JSONObject.parseObject(message);
        //转化为对象
        DealData crawlerData = JSONObject.toJavaObject(jsonObject, DealData.class);

        //获取经纬信息
        AddressDetail addressDetail = crawlerData.getAddressDetail();

        String company_info_id = jsonObject.getString("company_info_id");
        String company_name = crawlerData.getCompanyAll().getCompany_name();

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
        ArrayList<PersonInfo> listPersonInfo = new ArrayList<>();

        //遍历查询
        for (Keymans keymans:keymanslist) {

            //去掉公司
            String keymanName = keymans.getKeymanName();
            if (keymanName.equals("") || keymanName == null || keymanName.equals("-") || keymanName.contains("公司")) {
                continue;
            }

            PersonInfo personInfo = new PersonInfo();
            personInfo.setKeymanJob(keymans.getKeymanJob());
            personInfo.setKeymanName(keymanName);

            //判断是股东还是主要人员
            if (keymans.getKeymanJob().equals("股东")) {
                personInfo.setPeople_type("1");
            } else {
                personInfo.setPeople_type("2");
            }

            //添加
            listPersonInfo.add(personInfo);

        }


        JSONObject personJson = new JSONObject();

        personJson.put("company_name",company_name);
        personJson.put("company_info_id",company_info_id);
        personJson.put("listPersonInfo",listPersonInfo);
        personJson.put("addressDetail",addressDetail);

        String jsonString = personJson.toJSONString();

//        System.out.println(jsonString);

        return jsonString;

    }
}
