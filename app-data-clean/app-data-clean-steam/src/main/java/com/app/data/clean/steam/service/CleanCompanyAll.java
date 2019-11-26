package com.app.data.clean.steam.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.app.data.clean.steam.data.AbstractCleanData;
import jodd.util.StringUtil;
import org.springframework.stereotype.Service;

@Service
public class CleanCompanyAll extends AbstractCleanData {


    @Override
    public String getKey() {
        return "company_all";
    }

    @Override
    public JSONObject cleanData(JSONObject jsonObject) throws Exception {
        if (!jsonObject.containsKey("companyName"))
            return null;
        JSONObject caj = jsonObject;
        if (caj.containsKey("company_used_name")) {
            String company_used_name = caj.getString("company_used_name");
            if (company_used_name.equals("")) {
                caj.put("company_used_name", "");
            } else {
                if (company_used_name.equals("暂无信息")) {
                    caj.put("company_used_name", "");
                } else {
                    JSONArray jsonArray = JSONArray.parseArray(company_used_name);
                    String sb = "";
                    for (Object o : jsonArray) {
                        if (sb == "") {
                            sb = o.toString();
                        } else {
                            sb = sb + "," + o.toString();
                        }
                    }
                    caj.put("company_used_name", sb);
                }
            }
        }

        if (caj.containsKey("company_email")) {
            String company_email = caj.getString("company_email");
            if (company_email.equals("")) {
                caj.put("company_email", "");
            } else {
                if (company_email.equals("暂无信息")) {
                    caj.put("company_email", "");
                } else if (company_email.equals(",")) {
                    JSONArray jsonArray = JSONArray.parseArray(company_email);
                    String sx = "";
                    for (Object o : jsonArray) {
                        if (sx == "") {
                            sx = o.toString();
                        } else {
                            sx = sx + "," + o.toString();
                        }
                    }
                    caj.put("company_email", sx);
                } else {
                    caj.put("company_email", company_email);
                }
            }
        }

        if (caj.containsKey("company_tel")) {
            String company_tel = caj.getString("company_tel");
            if (company_tel.equals("")) {
                caj.put("company_tel", "");
            } else {
                if (company_tel.equals("暂无信息")) {
                    caj.put("company_tel", "");
                } else if (company_tel.equals(",")) {
                    JSONArray jsonArray = JSONArray.parseArray(company_tel);
                    String sd = "";
                    for (Object o : jsonArray) {
                        if (sd == "") {
                            sd = o.toString();
                        } else {
                            sd = sd + "," + o.toString();
                        }
                    }
                    caj.put("company_tel", sd);
                } else {
                    caj.put("company_tel", company_tel);
                }
            }
        }
        String address = caj.getString("address");
        String reg_address = caj.getString("reg_address");
        //去除非标准字段
        address = buildAddress(address, reg_address);
        address = buildAddress(address, null);
        caj.put("address", address);
//        String data = JSON.toJSONString(caj, SerializerFeature.DisableCircularReferenceDetect);
        return caj;
    }

    private String buildAddress(String address, String def) {
        try {
            address = StringUtil.remove(address, "-");
            address = StringUtil.remove(address, "地址：");
            if (StringUtil.isBlank(address)) {
                address = def;
            }
        } catch (Exception e) {
            return def;
        }
        return address;
    }


    @Override
    public JSONArray cleanArray(JSONArray jsonArray) throws Exception {
        return null;
    }
}
