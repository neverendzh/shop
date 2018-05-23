package com.neverend.model;

import java.io.Serializable;

public class DindDanModel implements Serializable {
    private Integer count;
    private double subtotal;
    private double total;
    private Integer state;
    private String image;
    private Double shopPrice;
    private String pname;
    private Integer oid;
    private Integer pid;

    @Override
    public String toString() {
        return "DindDanModel{" +
                "count=" + count +
                ", subtotal=" + subtotal +
                ", total=" + total +
                ", state=" + state +
                ", image='" + image + '\'' +
                ", shopPrice=" + shopPrice +
                ", pname='" + pname + '\'' +
                ", oid=" + oid +
                ", pid=" + pid +
                '}';
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Double getShopPrice() {
        return shopPrice;
    }

    public void setShopPrice(Double shopPrice) {
        this.shopPrice = shopPrice;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }



    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public Integer getOid() {
        return oid;
    }

    public void setOid(Integer oid) {
        this.oid = oid;
    }
}
