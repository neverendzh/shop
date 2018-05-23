package com.neverend.entity;

import java.io.Serializable;

/**
 * @author 
 */
public class Category implements Serializable {
    private Integer cid;

    private String cname;

    private static final long serialVersionUID = 1L;

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
}