package com.kgc.house.service;

import com.github.pagehelper.PageInfo;
import com.kgc.house.pojo.Users;
import com.kgc.house.utils.UserCondition;

import java.util.List;

public interface UsersService {


    List<Users> selectUsersAll();  //查询所有区域 区域实体集合

    PageInfo<Users> GetUsersByPage(UserCondition condition);

    int AddUsers(Users Users);

    Users SelectUsersOne(Integer id);

    int updata(Users Users);

    //实现单独删除的业务
    int DeleteUsers(Integer id) throws Exception;

    //批量删除
    public int delMoreUsers(List<Integer> ids);

    //用户查重
    public int checkUserName(String name);

    //用户注册与添加MD5加密
    public int addUser(Users users);

    //使用用户登录
    public Users login(String name, String password);


    Users loginTel(String tel);
}
