package com.kgc.house.service;

import com.github.pagehelper.PageInfo;
import com.kgc.house.pojo.House;
import com.kgc.house.utils.HouseCondition;
import com.kgc.house.utils.Page;

public interface HouseService {

    public int addHouse(House house);


    public PageInfo<House> getHouseByUser(Integer id, Page page);

    public House getHouse(String id);     //用于修改回显的查单条方法

    public int updateHouse(House house);// 修改的真正方法

    public int delHouse(String id, Integer isdel); //用于做逻辑删除的方法


    public PageInfo<House> getHouseByIsPass(Page page, Integer state);//用于查询没有审核通过的出租房方法

    public int HouseIsPass(String id, Integer ispass); //用于审核出租房的方法

    public PageInfo<House> getHouseBySeach(HouseCondition condition);


}
