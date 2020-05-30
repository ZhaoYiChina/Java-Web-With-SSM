package com.api.controller;

import com.google.gson.Gson;
import com.admin.entities.User;
import com.admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/apiGetUserMsg")
public class Servlet extends HttpServlet {

    @Autowired
    private UserService userService;

    //这里使用有参init（ServletConfig config）方法
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {



        //设置响应内容类型
        resp.setContentType("text/json;charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        List<User> userList = userService.getAllUser();
        Gson gson = new Gson();
        String message = gson.toJson(userList);
        //设置逻辑实现
        PrintWriter out = resp.getWriter();
        out.println(message);
        out.flush();
        out.close();
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
