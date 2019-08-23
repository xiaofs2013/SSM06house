package com.kgc.house.controller;

import com.github.pagehelper.PageInfo;
import com.kgc.house.pojo.Street;
import com.kgc.house.service.StreetService;
import com.kgc.house.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

@Controller
@RequestMapping("/admin")
public class StreetCollection {

    @Autowired
    private StreetService streetService;

    @RequestMapping("/getStreet")
    @ResponseBody
    public HashMap<String, Object> SelectStreetAll(Integer id, Page page) {

        //调用业务
        PageInfo<Street> pageInfo = streetService.getStreetByDid(id, page);

        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("total", pageInfo.getTotal());
        map.put("rows", pageInfo.getList());

        return map;

    }


    @RequestMapping("/DelectStreet")
    @ResponseBody
    public int DeleteStreet(Integer id) {

        int i = streetService.DeleteStreet(id);

        return i;
    }

}
