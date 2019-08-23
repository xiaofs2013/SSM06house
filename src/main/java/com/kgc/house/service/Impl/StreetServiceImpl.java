package com.kgc.house.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kgc.house.mapper.StreetMapper;
import com.kgc.house.pojo.Street;
import com.kgc.house.pojo.StreetExample;
import com.kgc.house.service.StreetService;
import com.kgc.house.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StreetServiceImpl implements StreetService {

    @Autowired
    private StreetMapper streetMapper;

    public PageInfo<Street> getStreetByDid(Integer id, Page page) {

        PageHelper.startPage(page.getPage(), page.getRows());

        StreetExample e = new StreetExample();
        StreetExample.Criteria c = e.createCriteria();
        c.andDistrictIdEqualTo(id);   //通过District的主键查询关联的Street的元素

        List<Street> list = streetMapper.selectByExample(e);
        PageInfo pageInfo = new PageInfo(list);

        return pageInfo;
    }

    public int DeleteStreet(Integer id) {

        int i = streetMapper.deleteByPrimaryKey(id);

        return i;
    }

    public List<Street> SelectStreetAllById(Integer id) {

        StreetExample e = new StreetExample();
        StreetExample.Criteria c = e.createCriteria();
        c.andIdEqualTo(id);

        List<Street> streets = streetMapper.selectByExample(e);

        return streets;
    }
}
