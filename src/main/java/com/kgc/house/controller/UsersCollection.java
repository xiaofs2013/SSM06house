package com.kgc.house.controller;

import com.github.pagehelper.PageInfo;
import com.kgc.house.pojo.Users;
import com.kgc.house.service.Impl.UsersServiceImpl;
import com.kgc.house.utils.UserCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class UsersCollection {


    @Autowired
    private UsersServiceImpl UsersService;

    @RequestMapping("/getUsers")
    @ResponseBody
    public HashMap<String, Object> selectUsersAll(UserCondition condition) {
        List<Users> Userss = UsersService.selectUsersAll();


        //以下是分页的操作
        //1.查询所有的学生
        PageInfo<Users> pageInfo = UsersService.GetUsersByPage(condition);
        //2.为了满足datagrid控件实现分页，必须返回total与rows键
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("total", pageInfo.getTotal());
        map.put("rows", pageInfo.getList());


        return map;
    }

    @RequestMapping("/AddUsers")
    @ResponseBody
    public String AddUsers(Users Users) {
        int temp = -1;

        try {

            temp = UsersService.AddUsers(Users);

        } catch (Exception e) {

        }

        return "{\"result\":" + temp + "}";
    }


    @RequestMapping("/SelectUsersOne")
    @ResponseBody
    public Users getUsers(Integer id) {

        Users Users = UsersService.SelectUsersOne(id);

        return Users;

    }

    @RequestMapping("/UpdataUsers")
    @ResponseBody
    public String updata(Users Users) {

        int i = -1;


        try {
            i = UsersService.updata(Users);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "{\"result\":" + i + "}";
    }

    @RequestMapping("/DeleteUsers")
    @ResponseBody
    public String DeleteUsers(Integer id) {
        int i = -1;

        try {
            i = UsersService.DeleteUsers(id);
        } catch (Exception e) {

        }


        return "{\"result\":" + i + "}";
    }

    @RequestMapping("delMoreUsers")
    @ResponseBody
    public String delMoreUsers(String id) {//接收编号，名称
        //id=1,2,3,4
        //分割字符串
        String[] arys = id.split(",");
        //转化为List
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < arys.length; i++) {
            list.add(Integer.parseInt(arys[i]));
        }
        //调用业务
        int temp = UsersService.delMoreUsers(list);
        return "{\"result\":" + temp + "}";
    }


}

