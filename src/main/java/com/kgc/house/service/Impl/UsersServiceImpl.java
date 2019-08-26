package com.kgc.house.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kgc.house.mapper.UsersMapper;
import com.kgc.house.pojo.Users;
import com.kgc.house.pojo.UsersExample;
import com.kgc.house.service.UsersService;
import com.kgc.house.utils.MD5Utils;
import com.kgc.house.utils.UserCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersMapper usersMapper;

    public List<Users> selectUsersAll() {
        List<Users> users = usersMapper.selectByExample(null);
        return users;
    }

    public PageInfo<Users> GetUsersByPage(UserCondition condition) {

        PageHelper.startPage(condition.getPage(), condition.getRows());

        UsersExample e = new UsersExample();
        UsersExample.Criteria c = e.createCriteria();
        c.andIsadminEqualTo(1);   //只查询显示Isadmin为1的人员 （只显示管理员）


        //添加搜索条件
        if (condition.getName() != null && !condition.getName().equals("")) {
            c.andNameLike("%" + condition.getName() + "%");
        }

        if (condition.getTelephone() != null && !condition.getTelephone().equals("")) {
            c.andTelephoneLike("%" + condition.getTelephone() + "%");
        }

        /*我的表中没有AGE 所以没办法做Age的查询方法
        硬要查的话需要更改表 而且又需要逆向生成一次
        十分的麻烦   这里用注释将年龄的区间查询给贴出了解学习一下

        if(condition.getStartAge()!=null){
            criteria.andAgeGreaterThanOrEqualTo(condition.getStartAge());   //年龄大于或等于
        }
        if(condition.getEndAge()!=null){
            criteria.andAgeLessThanOrEqualTo(condition.getEndAge());   //年龄小于或等于
        }

        */


        List<Users> list = usersMapper.selectByExample(e);

        PageInfo pageInfo = new PageInfo(list);


        return pageInfo;
    }

    public int AddUsers(Users Users) {

        int i = usersMapper.insertSelective(Users);

        return i;
    }

    public Users SelectUsersOne(Integer id) {

        Users users = usersMapper.selectByPrimaryKey(id);

        return users;
    }

    public int updata(Users Users) {

        int i = usersMapper.updateByPrimaryKeySelective(Users);

        return i;
    }

    public int DeleteUsers(Integer id) throws Exception {

        int i = usersMapper.deleteByPrimaryKey(id);

        return i;
    }

    public int delMoreUsers(List<Integer> ids) {

        int i = usersMapper.delMoreUsers(ids);

        return i;
    }

    public int checkUserName(String name) {

        UsersExample e = new UsersExample();
        UsersExample.Criteria c = e.createCriteria();
        c.andNameEqualTo(name);
        c.andIsadminEqualTo(0);     //表示普通用户

        List<Users> list = usersMapper.selectByExample(e);

        return list.size();    //返回数值有多少个元素
    }

    public int addUser(Users users) {

        users.setIsadmin(0);   //设置为普通用户

        String s = MD5Utils.md5Encrypt(users.getPassword());  //使用MD5加密

        users.setPassword(s);   //使用MD5将加密后的密码存进去方便添加

        int i = usersMapper.insertSelective(users);

        return i;
    }

    public Users login(String name, String password) {

        UsersExample e = new UsersExample();
        UsersExample.Criteria c = e.createCriteria();
        c.andNameEqualTo(name);
        String s = MD5Utils.md5Encrypt(password);  //使用MD5进行加密编译
        c.andPasswordEqualTo(s); //将编译过后的MD5密码进行解析

        List<Users> list = usersMapper.selectByExample(e);

        if (list.size() == 0) {
            return null;
        } else {
            return list.get(0);    //获得list中的第一个元素 应该是ID或者Name
        }
    }

    public Users loginTel(String tel) {

        UsersExample e = new UsersExample();
        UsersExample.Criteria c = e.createCriteria();
        c.andTelephoneEqualTo(tel);
        List<Users> list = usersMapper.selectByExample(e);
        if (list.size() == 0) {
            return null;
        } else {
            return list.get(0);
        }


    }
}
