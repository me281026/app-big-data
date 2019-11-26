/**
  * Copyright 2019 bejson.com 
  */
package com.app.data.clean.entity;
import lombok.Data;

/**
 * Auto-generated: 2019-05-08 22:34:39
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
@Data
public class ProductVies {

    private String productVieIndustry;
    private String productVieRegion;
    private String productVieBusiness;
    private String productVieValue;
    private String productVieRegDate;
    private String productVieSeries;
    private String productVieName;


    public String getProductVieIndustry() {
        return productVieIndustry;
    }

    public void setProductVieIndustry(String productVieIndustry) {
        this.productVieIndustry = productVieIndustry;
    }

    public String getProductVieRegion() {
        return productVieRegion;
    }

    public void setProductVieRegion(String productVieRegion) {
        this.productVieRegion = productVieRegion;
    }

    public String getProductVieBusiness() {
        return productVieBusiness;
    }

    public void setProductVieBusiness(String productVieBusiness) {
        this.productVieBusiness = productVieBusiness;
    }

    public String getProductVieValue() {
        return productVieValue;
    }

    public void setProductVieValue(String productVieValue) {
        this.productVieValue = productVieValue;
    }

    public String getProductVieRegDate() {
        return productVieRegDate;
    }

    public void setProductVieRegDate(String productVieRegDate) {
        this.productVieRegDate = productVieRegDate;
    }

    public String getProductVieSeries() {
        return productVieSeries;
    }

    public void setProductVieSeries(String productVieSeries) {
        this.productVieSeries = productVieSeries;
    }

    public String getProductVieName() {
        return productVieName;
    }

    public void setProductVieName(String productVieName) {
        this.productVieName = productVieName;
    }
}