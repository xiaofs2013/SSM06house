package com.kgc.house.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kgc.house.mapper.DistrictMapper;
import com.kgc.house.mapper.StreetMapper;
import com.kgc.house.pojo.District;
import com.kgc.house.pojo.DistrictExample;
import com.kgc.house.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
public class DistrictServiceImpl implements DistrictService {
   @Autowired
    private DistrictMapper districtMapper;

    @Autowired
    private StreetMapper streetMapper;


    public List<District> selectDistrictAll() {

        //封装条件实体类
        List<District> districts = districtMapper.selectByExample(null);

        return districts;
    }

    public PageInfo<District> GetDistrictByPage(int page, int pagesize) {

        //1 开启分页
        PageHelper.startPage(page, pagesize); //里面传页码与每页显示数即可
        //2.查询所有的信息
        List<District> list = districtMapper.selectByExample(null);
        //3.获取分页的相关信息
        PageInfo pageInfo = new PageInfo(list);


        return pageInfo;
    }

    public int AddDistrict(District district) {

        int i = districtMapper.insertSelective(district);

        return i;
    }

    public District SelectOne(Integer id) {

        District district = districtMapper.selectByPrimaryKey(id);

        return district;
    }

    public int updata(District district) {

        int i = districtMapper.updateByPrimaryKeySelective(district);

        return i;
    }

    //只有业务会添加事务支持
    @Transactional //添加事务的注解  只要添加了这个注解  事务就会启动  非常的方便
    public int DeleteDistrict(Integer id) {


        //删除街道
        int i1 = streetMapper.deleteStreetByDid(id);

        //删除区域
        int i = districtMapper.deleteByPrimaryKey(id);

        return i;


    }


    public int delMoreDistrict(List<Integer> ids) {
        return districtMapper.delMoreDistrict(ids);
    }




}
