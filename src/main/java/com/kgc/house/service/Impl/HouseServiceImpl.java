package com.kgc.house.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kgc.house.mapper.HouseMapper;
import com.kgc.house.pojo.House;
import com.kgc.house.service.HouseService;
import com.kgc.house.utils.HouseCondition;
import com.kgc.house.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HouseServiceImpl implements HouseService {

    @Autowired
    private HouseMapper houseMapper;

    public int addHouse(House house) {

        int i = houseMapper.insertSelective(house);

        return i;
    }


    public PageInfo<House> getHouseByUser(Integer id, Page page) {
        PageHelper.startPage(page.getPage(), page.getRows());
        //查询所有记录
        List<House> list = houseMapper.getHouseByUserId(id);
        return new PageInfo<House>(list);
    }

    public House getHouse(String id) {
        House house = houseMapper.getHouseById(id);  //这个方法源自mapper层的自定义方法
        return house;
    }

    public int updateHouse(House house) {
        int i = houseMapper.updateByPrimaryKeySelective(house);
        return i;
    }

    public int delHouse(String id, Integer isdel) {
        //就只需要获得两个参数 一个ID用来锁定 一个indel更改他的状态
        //0代表正常 1代表删除

        House house = new House();
        house.setId(id);
        house.setIsdel(1);
        int i = houseMapper.updateByPrimaryKeySelective(house);
        return i;
    }

    public PageInfo<House> getHouseByIsPass(Page page, Integer state) {
        PageHelper.startPage(page.getPage(), page.getRows());
        List<House> houseByIsPass = houseMapper.getHouseByIsPass(state);
        return new PageInfo<House>(houseByIsPass);
    }

    public int HouseIsPass(String id, Integer ispass) {

        //这个是用来审核的方法  跟上面逻辑删除的步骤其实是一样的

        House house = new House();
        house.setId(id);
        house.setIspass(ispass);
        int i = houseMapper.updateByPrimaryKeySelective(house);

        return i;
    }


    //带有分页的添加了条件的查询方法  HouseCondition类中继承了Page  所以可以使用它的分页
    public PageInfo<House> getHouseBySeach(HouseCondition condition) {

        PageHelper.startPage(condition.getPage(), condition.getRows());
        System.out.println("检查输出值getPage:" + condition.getPage());
        System.out.println("检查输出值getRows:" + condition.getRows());
        List<House> list = houseMapper.getHouseBySearch(condition);
        return new PageInfo<House>(list);
    }


}
