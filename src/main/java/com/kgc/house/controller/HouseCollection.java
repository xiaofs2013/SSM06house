package com.kgc.house.controller;

import com.github.pagehelper.PageInfo;
import com.kgc.house.pojo.House;
import com.kgc.house.service.HouseService;
import com.kgc.house.utils.HouseCondition;
import com.kgc.house.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class HouseCollection {

    @Autowired
    private HouseService houseService;


    //0表示未审核
    //1表示审核
    //用于查询未审核的方法
    @RequestMapping("/getHouseNoPass")
    @ResponseBody   // 如果你发现 明明代码都查出来了 但是AJAX没有显示数据或者EasyUI没有显示数据 那么就是你忘记加这个注解了
    public Map<String, Object> getHouseNoPass(Page page) {
        //创建map
        PageInfo<House> pageInfo = houseService.getHouseByIsPass(page, 0);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("total", pageInfo.getTotal());   //分页的操作（EasyUI前提下的分页）
        map.put("rows", pageInfo.getList());

        return map;
    }

    //用于个未审核的数据进行审核操作的方法
    @RequestMapping("/houseYes")
    @ResponseBody
    public String houseYes(String id) {

        int i = houseService.HouseIsPass(id, 1);

        return "{\"result\":" + i + "}";  //当你看到result  就说明这个方法他被异步请求用了  一定要加@ResponseBody
    }

    //0表示未审核
    //1表示审核
    //用于查询已经审核通过的数据
    @RequestMapping("/getHouseYesPass")
    @ResponseBody   // 如果你发现 明明代码都查出来了 但是AJAX没有显示数据或者EasyUI没有显示数据 那么就是你忘记加这个注解了
    public Map<String, Object> getHouseYesPass(Page page) {
        //创建map
        PageInfo<House> pageInfo = houseService.getHouseByIsPass(page, 1);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("total", pageInfo.getTotal());   //分页的操作（EasyUI前提下的分页）
        map.put("rows", pageInfo.getList());

        return map;
    }

    //用于给审核的数据进行打回审核操作的方法
    @RequestMapping("/houseNo")
    @ResponseBody
    public String houseNo(String id) {

        int i = houseService.HouseIsPass(id, 0);

        return "{\"result\":" + i + "}";  //当你看到result  就说明这个方法他被异步请求用了
    }





}
