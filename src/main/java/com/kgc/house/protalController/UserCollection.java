package com.kgc.house.protalController;

import com.kgc.house.pojo.Users;
import com.kgc.house.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/page")
public class UserCollection {

    @Autowired
    private UsersService usersService;

    @RequestMapping("/checkUname")
    @ResponseBody
    public String CheckName(String name) {

        //调用业务
        int i = usersService.checkUserName(name);

        return "{\"result\":" + i + "}";

    }

    @RequestMapping("/reg")
    public String reg(Users users) {
        int i = usersService.addUser(users);

        if (i > 0) {
            return "login";
        } else {
            return "regs";
        }

    }

    @RequestMapping("loginAction")
    public String loginAction(String name, String password, HttpSession session, Model model) {

        Users users = usersService.login(name, password);

        if (users == null) {
            model.addAttribute("info", "用户名或者密码错误，请重新输入！");
            return "login";
        } else {
            session.setAttribute("loginInfo", users);
            session.setMaxInactiveInterval(600);
            return "redirect:getHouse";  //用户登录后的网页
        }

    }

    //实现登入：通过手机验证
    @RequestMapping("/login2")
    public String checkUserName(String inputCode, HttpSession session, String tel, Model model) {

        String code = (String) session.getAttribute("code");
        Users users = usersService.loginTel(tel);

        if (inputCode.equals(code) && users != null) {
            session.setAttribute("loginInfo", users);
            session.setMaxInactiveInterval(600);
            return "redirect:getHouse";  //用户登录后的网页

        } else {
            model.addAttribute("info", "手机号码不正确！");
            return "login";
        }
    }


}
