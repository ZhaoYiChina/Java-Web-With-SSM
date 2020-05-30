package com.api.controller;

import com.admin.entities.User;
import com.admin.service.UserService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/LoginActionServlet/login.action")
public class LoginActionServlet extends HttpServlet {

    @Autowired
    private UserService userService;

    //这里使用有参init（ServletConfig config）方法
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        //1.获取URI
        String uri = request.getRequestURI();
        HttpSession session = request.getSession();
        //2.截取URI中的动作
        uri = uri.substring(uri.lastIndexOf("/"),uri.lastIndexOf("."));
        if("/login".equals(uri)){
            //判断用户名和密码
            String uname = request.getParameter("name");
            String pwd = request.getParameter("pwd");
            User user = new User();
            user.setName(uname);
            user.setPassword(pwd);
            try{
                //登录认证
                if(userService.auth(user)==false){
                    //登录失败
                    //request.setAttribute("msg","用户名或密码不正确");
                    response.getWriter().print("{\"result\":\"fail\",\"message\":\"username or password is wrong\"}");
                }else{
                    //登录成功,记录用户信息到session中
                    session.setAttribute("uname",uname);
                    //session过期时间设置为5分钟
                    session.setMaxInactiveInterval(300);
                    response.getWriter().print("{\"result\":\"success\",\"message\":\"login success\"}");
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void destroy() {
        super.destroy();
    }

//    http://localhost:8080/LoginActionServlet/login.action?name=zhaoyi&pwd=zhaoyi0923

}
