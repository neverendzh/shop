package com.neverend.service.impl;

import com.neverend.entity.Categorysecond;
import com.neverend.entity.CategorysecondExample;
import com.neverend.mapper.CategorysecondMapper;
import com.neverend.service.CategorysecondService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategorysecondServiceImpl implements CategorysecondService {
    @Autowired
    private CategorysecondMapper categorysecondMapper;
    @Override
    public Categorysecond selectCays(String cids) {
        CategorysecondExample categorysecondExample = new CategorysecondExample();
        categorysecondExample.createCriteria().andCsidEqualTo(Integer.valueOf(cids));
        List<Categorysecond> categoryseconds = categorysecondMapper.selectByExample(categorysecondExample);
        if (!categoryseconds.isEmpty()){
            return categoryseconds.get(0);
        }
        return null;
    }

    @Override
    public List<Categorysecond> selectCateSencondAll() {
        CategorysecondExample categorysecondExample = new CategorysecondExample();
        List<Categorysecond> categoryseconds = categorysecondMapper.selectByExample(categorysecondExample);
        return categoryseconds;
    }

    @Override
    public Categorysecond selectCaysName(String name) {
        CategorysecondExample categorysecondExample = new CategorysecondExample();
        categorysecondExample.createCriteria().andCsnameEqualTo(name);
        List<Categorysecond> categoryseconds = categorysecondMapper.selectByExample(categorysecondExample);
        if (!categoryseconds.isEmpty()){
            Categorysecond categorysecond = categoryseconds.get(0);
            return categorysecond;
        }
        return null;
    }

    @Override
    public void saveCateSend(String name,Integer cid) {
        Categorysecond categorysecond = new Categorysecond();
        categorysecond.setCsname(name);
        categorysecond.setCid(cid);
        categorysecondMapper.insert(categorysecond);
    }

    @Override
    public void delcateys(Integer integer) {
        Categorysecond categorysecond = new Categorysecond();
        categorysecond.setCsid(integer);
        categorysecondMapper.updateByPrimaryKey(categorysecond);
        categorysecondMapper.deleteByPrimaryKey(integer);
    }

    @Override
    public Categorysecond selectCsid(String csid) {
        CategorysecondExample categorysecondExample = new CategorysecondExample();
        categorysecondExample.createCriteria().andCsidEqualTo(Integer.valueOf(csid));
        List<Categorysecond> categoryseconds = categorysecondMapper.selectByExample(categorysecondExample);
        if (!categoryseconds.isEmpty()){
            Categorysecond categorysecond = categoryseconds.get(0);
            return categorysecond;
        }
        return null;
    }

    @Override
    public void updateCateSend(String csid, String name,String cid) {
        Categorysecond categorysecond = new Categorysecond();
        categorysecond.setCsid(Integer.valueOf(csid));
        categorysecond.setCid(Integer.valueOf(cid));
        categorysecond.setCsname(name);
        categorysecondMapper.updateByPrimaryKey(categorysecond);
    }

    @Override
    public List<Categorysecond> selectCaySecondAllCaID(Integer integer) {
        CategorysecondExample categorysecondExample = new CategorysecondExample();
        categorysecondExample.createCriteria().andCidEqualTo(integer);
        List<Categorysecond> categoryseconds  = categorysecondMapper.selectByExample(categorysecondExample);
        return categoryseconds;
    }
}
