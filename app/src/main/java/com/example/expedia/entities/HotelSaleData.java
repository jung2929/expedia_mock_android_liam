package com.example.expedia.entities;

public class HotelSaleData {
    private String name;
    private String shortL;
    private String salePercentage;
    private String sDate;
    private String eDate;
    private String priced;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortL() {
        return shortL;
    }

    public void setShortL(String shortL) {
        this.shortL = shortL;
    }

    public String getSalePercentage() {
        return salePercentage;
    }

    public void setSalePercentage(String salePercentage) {
        this.salePercentage = salePercentage;
    }

    public String getsDate() {
        return sDate;
    }

    public void setsDate(String sDate) {
        this.sDate = sDate;
    }

    public String geteDate() {
        return eDate;
    }

    public void seteDate(String eDate) {
        this.eDate = eDate;
    }

    public String getPriced() {
        return priced;
    }

    public void setPriced(String priced) {
        this.priced = priced;
    }
}
