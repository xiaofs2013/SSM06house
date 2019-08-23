package com.kgc.house.service;

import com.github.pagehelper.PageInfo;
import com.kgc.house.pojo.District;
import com.kgc.house.pojo.Type;

import java.util.List;

public interface TypeService {


    List<Type> selectTypeAll();  //查询所有区域 区域实体集合

    PageInfo<Type> GetTypeByPage(int page, int pagesize);

    int AddType(Type type);

    Type SelectTypeOne(Integer id);

    int updata(Type type);

    //实现单独删除的业务
    int DeleteType(Integer id) throws Exception;

    //批量删除
    public int delMoreType(List<Integer> ids);
}
