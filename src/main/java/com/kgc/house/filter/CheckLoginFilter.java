package com.kgc.house.filter;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CheckLoginFilter implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain) throws IOException, ServletException {

        System.out.println("想进去吗？");

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        String url = request.getRequestURI();  //可以获取到每次访问页面的地址
        String path = url.substring(url.lastIndexOf("/") + 1);
        System.out.println(url);   //     /page/login.jsp
        System.out.println(path);  //     login.jsp

        //LastIndexOf取的是“.”最后一次出现的索引，加1后取得是"."最后一次出现的索引的后一位

        if (path.equals("login.jsp") || path.equals("loginAction")) {    //  放行操作  让用户登录  一个是页面 一个是控制器的URL
            filterChain.doFilter(req, resp);
        } else {
            //判断你有没有登录
            HttpSession session = request.getSession();
            Object o = session.getAttribute("loginInfo");//控制器内存入Seesion的对象名
            if (o == null) {
                response.sendRedirect("login.jsp");   //sendRedirect 重定向
            } else {
                filterChain.doFilter(req, resp);
            }
        }


    }

    public void destroy() {

    }
}
