package com.admin.controller;

import com.admin.entities.User;
import com.admin.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class UserController {

    private static final Logger logger = Logger.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping("/user")
    public ModelAndView user(){
        ModelAndView modelAndView = new ModelAndView("user");
        List<User> userList = userService.getAllUser();
        System.out.println(userList);
        modelAndView.addObject("user1", userList.get(0));
        modelAndView.addObject("user2", userList.get(1));

        return modelAndView;
    }

    @RequestMapping(value = "/say", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String sayHi(HttpServletRequest request, HttpServletResponse response){
         String name = request.getParameter("v");
        List<User> users = userService.getAllUser();
        logger.info("{name:jay,context:hi,你好}");
        return name+"-{name:jay,context:hi,你好}"+users;
    }

    @RequestMapping("/hello")
    public String hello(ModelMap modelMap){
        String w1 = "Hello";
        String w2 = "World!";
        String w3 = "My 1st project.";
        String w4 = "Hahahahaha ^_^";
        modelMap.addAttribute("w1", w1);
        modelMap.addAttribute("w2", w2);
        modelMap.addAttribute("w3", w3);
        modelMap.addAttribute("w4", w4);
        return "hello";
    }

    @RequestMapping("/login")
    public String login(ModelMap modelMap){
        modelMap.addAttribute("pageName","登录");
        User user = userService.load(1);
        modelMap.addAttribute("userId",user.getId());
        modelMap.addAttribute("userName",user.getName());
        return "login";
    }


}
