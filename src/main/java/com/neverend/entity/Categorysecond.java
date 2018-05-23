package com.neverend.entity;

import java.io.Serializable;

/**
 * @author 
 */
public class Categorysecond implements Serializable {
    private Integer csid;

    private String csname;

    private Integer cid;

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        return "Categorysecond{" +
                "csid=" + csid +
                ", csname='" + csname + '\'' +
                ", cid=" + cid +
                '}';
    }

    public Integer getCsid() {
        return csid;
    }

    public void setCsid(Integer csid) {
        this.csid = csid;
    }

    public String getCsname() {
        return csname;
    }

    public void setCsname(String csname) {
        this.csname = csname;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }
}