package com.kgc.house.controller;

import com.github.pagehelper.PageInfo;
import com.kgc.house.pojo.District;
import com.kgc.house.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class DistrictCollection {


    @Autowired
    private DistrictService districtService;

    @RequestMapping("/getDistrict")
    @ResponseBody
    public HashMap<String, Object> selectDistrictAll(int page, int rows) {
        List<District> districts = districtService.selectDistrictAll();


        //以下是分页的操作
        //1.查询所有的学生
        PageInfo<District> pageInfo = districtService.GetDistrictByPage(page, rows);
        //2.为了满足datagrid控件实现分页，必须返回total与rows键
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("total", pageInfo.getTotal());
        map.put("rows", pageInfo.getList());


        return map;
    }

    @RequestMapping("/AddDistrict")
    @ResponseBody
    public String AddDistrict(District district) {
        int temp = -1;

        try {

            temp = districtService.AddDistrict(district);

        } catch (Exception e) {

        }

        return "{\"result\":" + temp + "}";
    }


    @RequestMapping("/SelectOne")
    @ResponseBody
    public District getDistrict(Integer id) {

        District district = districtService.SelectOne(id);

        return district;

    }

    @RequestMapping("/UpdataDistrict")
    @ResponseBody
    public String updata(District district) {

        int i = -1;


        try {
            i = districtService.updata(district);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "{\"result\":" + i + "}";
    }

    @RequestMapping("/DeleteDistrict")
    @ResponseBody
    public String DeleteDistrict(Integer id) {
        int i = -1;

        try {
            i = districtService.DeleteDistrict(id);
        } catch (Exception e) {

        }


        return "{\"result\":" + i + "}";
    }

    @RequestMapping("/delMoreDistrict")
    @ResponseBody
    public String delMoreDistrict(String id) {//接收编号，名称
        //id=1,2,3,4
        //分割字符串
        String[] arys = id.split(",");
        //转化为List
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < arys.length; i++) {
            list.add(Integer.parseInt(arys[i]));
        }
        //调用业务
        int temp = districtService.delMoreDistrict(list);
        return "{\"result\":" + temp + "}";
    }


}
