package com.kgc.house.service;

import com.github.pagehelper.PageInfo;
import com.kgc.house.pojo.District;

import java.util.List;

public interface DistrictService {


    List<District> selectDistrictAll();  //查询所有区域 区域实体集合

    PageInfo<District> GetDistrictByPage(int page, int pagesize);

    int AddDistrict(District district);

    District SelectOne(Integer id);

    int updata(District district);

    //实现单独删除的业务
    int DeleteDistrict(Integer id) throws Exception;

    //批量删除
    public int delMoreDistrict(List<Integer> ids);


}
