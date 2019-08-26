package com.kgc.house.protalController;


import com.kgc.house.sms.SmsUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/page")
public class SmsController {

    @RequestMapping("/sendCode")
    @ResponseBody
    public Map<String, Object> sendCode(String tel, HttpSession session) {

        System.out.println("进入了SMS方法");


        //产生的随机数为：0.1234567
        String str = Math.random() + "";
        //截取下标2与6之间
        String code = str.substring(2, 6);
        //定义发送消息的内容
        String msg = "登入的验证码为：" + code;

        System.out.println(msg);

        //非常重要的一个步骤  为了让页面和输入的验证码做对比 我们必须要将验证码存入Session中进行对比
        session.setAttribute("code", code);
        session.setMaxInactiveInterval(60);


        int temp = SmsUtil.sendMsg(tel, msg);    //得到发送消息的状态码

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("result", temp);


        return map;
    }
}
