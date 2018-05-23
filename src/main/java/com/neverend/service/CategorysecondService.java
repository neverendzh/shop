package com.neverend.service;

import com.neverend.entity.Categorysecond;

import java.util.List;

public interface CategorysecondService {

    Categorysecond selectCays(String cids);

    List<Categorysecond> selectCateSencondAll();

    Categorysecond selectCaysName(String name);

    void saveCateSend(String name,Integer cid);

    void delcateys(Integer integer);

    Categorysecond selectCsid(String csid);

    void updateCateSend(String csid, String name,String cid);

    List<Categorysecond> selectCaySecondAllCaID(Integer integer);
}
