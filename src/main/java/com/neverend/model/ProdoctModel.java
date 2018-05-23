package com.neverend.model;

import com.neverend.entity.Categorysecond;
import org.apache.ibatis.annotations.One;

import java.io.Serializable;
import java.util.List;

public class ProdoctModel implements Serializable {

    private Integer cid;

    private String cname;

    private static final long serialVersionUID = 1L;


    
    List<Categorysecond> categoryseconds;

    @Override
    public String toString() {
        return "ProdoctModel{" +
                "cid=" + cid +
                ", cname='" + cname + '\'' +
                ", categoryseconds=" + categoryseconds +
                '}';
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public List<Categorysecond> getCategoryseconds() {
        return categoryseconds;
    }

    public void setCategoryseconds(List<Categorysecond> categoryseconds) {
        this.categoryseconds = categoryseconds;
    }
}
