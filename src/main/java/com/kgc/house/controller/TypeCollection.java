package com.kgc.house.controller;

import com.github.pagehelper.PageInfo;
import com.kgc.house.pojo.Type;
import com.kgc.house.pojo.Type;
import com.kgc.house.service.Impl.TypeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class TypeCollection {


    @Autowired
    private TypeServiceImpl typeService;

    @RequestMapping("/getType")
    @ResponseBody
    public HashMap<String, Object> selectTypeAll(int page, int rows) {
        List<Type> Types = typeService.selectTypeAll();


        //以下是分页的操作
        //1.查询所有的学生
        PageInfo<Type> pageInfo = typeService.GetTypeByPage(page, rows);
        //2.为了满足datagrid控件实现分页，必须返回total与rows键
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("total", pageInfo.getTotal());
        map.put("rows", pageInfo.getList());


        return map;
    }

    @RequestMapping("/AddType")
    @ResponseBody
    public String AddType(Type type) {
        int temp = -1;

        try {

            temp = typeService.AddType(type);

        } catch (Exception e) {

        }

        return "{\"result\":" + temp + "}";
    }


    @RequestMapping("/SelectTypeOne")
    @ResponseBody
    public Type getType(Integer id) {

        Type type = typeService.SelectTypeOne(id);

        return type;

    }

    @RequestMapping("/UpdataType")
    @ResponseBody
    public String updata(Type type) {

        int i = -1;


        try {
            i = typeService.updata(type);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "{\"result\":" + i + "}";
    }

    @RequestMapping("/DeleteType")
    @ResponseBody
    public String DeleteType(Integer id) {
        int i = -1;

        try {
            i = typeService.DeleteType(id);
        } catch (Exception e) {

        }


        return "{\"result\":" + i + "}";
    }

    @RequestMapping("delMoreType")
    @ResponseBody
    public String delMoreType(String id) {//接收编号，名称
        //id=1,2,3,4
        //分割字符串
        String[] arys = id.split(",");
        //转化为List
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < arys.length; i++) {
            list.add(Integer.parseInt(arys[i]));
        }
        //调用业务
        int temp = typeService.delMoreType(list);
        return "{\"result\":" + temp + "}";
    }


}

