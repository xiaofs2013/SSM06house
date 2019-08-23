package com.kgc.house.protalController;

import com.github.pagehelper.PageInfo;
import com.kgc.house.pojo.District;
import com.kgc.house.pojo.House;
import com.kgc.house.pojo.Type;
import com.kgc.house.pojo.Users;
import com.kgc.house.service.DistrictService;
import com.kgc.house.service.HouseService;
import com.kgc.house.service.TypeService;
import com.kgc.house.utils.HouseCondition;
import com.kgc.house.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.List;

@Controller
@RequestMapping("/page")
public class HouseController {

    @Autowired
    private TypeService typeService;

    @Autowired
    private DistrictService districtService;

    @Autowired
    private HouseService houseService;


    @RequestMapping("/goFaBu")
    public String goFaBu(Model model) {

        List<Type> list = typeService.selectTypeAll();

        List<District> list1 = districtService.selectDistrictAll();

        model.addAttribute("list", list);
        model.addAttribute("list1", list1);

        return "fabu";
    }

    @RequestMapping("/addHouse")
    public String addHouse(@RequestParam(value = "pfile", required = false) CommonsMultipartFile pfile, House house, HttpSession session, Model model
    ) {
        try {

            //RequestParam里面的value值就是jsp页面内图片的name名 强制和CommonsMultipartFile pfile对应
            //后面给的required 代表必须的要求 给与false  可有可无 不传也不会报错
            //一个CommonsMultipartFile类的对象就代表一个表单文件域，一张图片
            //获取上传文件的信息
            System.out.println("文件名" + pfile.getOriginalFilename());
            System.out.println("文件大小:" + pfile.getSize());
            System.out.println("文件类型:" + pfile.getContentType());
            System.out.println("文件字节:" + pfile.getBytes());


            //实现文件上传  第一步上传图片

            String path = "d:\\images\\";    //存放文件的地址

            //生成唯一文件名
            String oldName = pfile.getOriginalFilename();   //取得文件的原始名
            String expname = oldName.substring(oldName.lastIndexOf(".")); //取得文件的扩展名   从文件名的点开始取 就是扩展名
            String fileName = System.currentTimeMillis() + expname;  //用每一毫秒去拼接文件的后缀名


            File file = new File(path, fileName);   //IO流常识 一个文件 一个file
            // File file = new File(目标文件的存放位置);   需要两个参数 一个是地址 一个是文件名

            pfile.transferTo(file); // 设置将文件上传至哪儿   上传 保存


            //第二步 保存信息到数据库
            //设置发布出租房的用户
            Users users = (Users) session.getAttribute("loginInfo");  //取出session中用户登录后的域对象 也就是用户名
            house.setUserId(users.getId());   //取出他的ID 并存入house
            //house.setUserId(1000);   占位法 如果当公司的同僚落后于你的进度 你可以这样写以保障程序通过不被拖累

            //设置主键 因为主键没有设置自增长 而他又不能给默认值
            house.setId(System.currentTimeMillis() + "");  //给了个系统时间  加了个""  就能转化为成字符串类型

            //设置图片
            house.setPath(fileName);

            int i = houseService.addHouse(house);


            System.out.println("添加进入了" + i + "条数据");


            return "guanli";


        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("info", "文件上传失败...");
        }
        return "fabu";
    }


    //查询所有用户出租房信息   保需要传Page类中的page属性(页码)
    @RequestMapping("/getHouse")
    public String getHouse(Page page, HttpSession session, Model model) throws Exception {
        //调用业务
        //获取用户编号
        Integer uid = ((Users) session.getAttribute("loginInfo")).getId();
        PageInfo<House> pageInfo = houseService.getHouseByUser(uid, page);
        model.addAttribute("pageInfo", pageInfo);

        System.out.println("输出结果集" + pageInfo.getList());
        System.out.println("输出用户ID" + uid);
        System.out.println("输出page里面是什么" + page);

        return "guanli";
    }

    //修改肯定是需要回显的  弄了一个查询单条
    @RequestMapping("/getSingleHouse")
    public String getHouseOne(String id, Model model) {
        House house = houseService.getHouse(id);  //服务层里面调用了mapper层的方法
        model.addAttribute("HouseOne", house);
        return "showhouse";
    }

    //真正的修改
    @RequestMapping("/updateHouse")
    public String updateHouse(String delimage, House house, @RequestParam("pfile") CommonsMultipartFile pfile) {
        //page只为接收页码

        try {
            //判断用户是否选择文件
            String oldName = pfile.getOriginalFilename();   // pfile.getOriginalFilename();是获取文件的名字
            if (oldName.equals(""))  //没有选择文件
            {
                houseService.updateHouse(house);
            } else {
                //上传
                //第一步上传图片
                String path = "d:\\images\\";  //存放文件的位置
                //生成唯一文件名
                String expname = oldName.substring(oldName.lastIndexOf("."));
                String filename = System.currentTimeMillis() + expname;
                File file = new File(path + filename);
                pfile.transferTo(file);  //上传，保存
                //更新数据库
                //设置更新图片路径
                house.setPath(filename);
                houseService.updateHouse(house);
                //删除旧图
                File delfile = new File(path + delimage);
                delfile.delete();
            }
            return "redirect:getHouse";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "error";
    }

    @RequestMapping("/DelIsdel")
    public String DelIsdel(String id) {  //传个ID调业务就行了 后面的Isdel参数直接传1就行

        int i = houseService.delHouse(id, 1);   //逻辑删除的方法
        return "redirect:getHouse";    //重定向去管理
    }

    //返回前端的查询控制层 用于条件查询  搜索功能等
    @RequestMapping("/searchHouse")
    public String searchHouse(HouseCondition condition, Model model) {
        PageInfo<House> pageInfo = this.houseService.getHouseBySeach(condition);//返回的是pageinfo 这里肯定也要用pageinfo来接收

        model.addAttribute("condition", condition); //用于固定搜索条件的变量
        model.addAttribute("pageInfo", pageInfo);
        return "list";


    }


}
