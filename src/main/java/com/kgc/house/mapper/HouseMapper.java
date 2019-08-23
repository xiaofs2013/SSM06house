package com.kgc.house.mapper;

import com.kgc.house.pojo.House;
import com.kgc.house.pojo.HouseExample;
import com.kgc.house.utils.HouseCondition;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HouseMapper {
    long countByExample(HouseExample example);

    int deleteByExample(HouseExample example);

    int deleteByPrimaryKey(String id);

    int insert(House record);

    int insertSelective(House record);

    List<House> selectByExample(HouseExample example);

    House selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") House record, @Param("example") HouseExample example);

    int updateByExample(@Param("record") House record, @Param("example") HouseExample example);

    int updateByPrimaryKeySelective(House record);

    int updateByPrimaryKey(House record);

    public List<House> getHouseByUserId(Integer uid);

    public House getHouseById(String id);  //查询出租房信息 为了修改项目里面的回显

    public List<House> getHouseByIsPass(Integer ispass);  //完成审核操作的持久成方法

    public List<House> getHouseBySearch(HouseCondition condition);
    //在mapper层不要写pageinfo的返回值 没有用 直接写List condition类里面封装了条件 这样就是通过条件来查询数据

}