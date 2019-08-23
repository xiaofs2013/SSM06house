package com.kgc.house.service;

import com.github.pagehelper.PageInfo;
import com.kgc.house.pojo.Street;
import com.kgc.house.utils.Page;

import java.util.List;

public interface StreetService {

    PageInfo<Street> getStreetByDid(Integer id, Page page);

    int DeleteStreet(Integer id);


    List<Street> SelectStreetAllById(Integer did);
}
