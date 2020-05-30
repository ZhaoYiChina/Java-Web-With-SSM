package com.admin.service.impl;

import com.admin.dao.UserDao;
import com.admin.entities.User;
import com.admin.service.UserService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service("userServiceImpl")
@Scope("prototype")
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Override
    public List<User> getAllUser(){
        return userDao.getAllUsers();
    }

    @Override
    public User load(Integer id) {
        return userDao.load(id);
    }

    @Override
    public Boolean auth(User user ) {
        User userLogin = userDao.auth(user);
        if(null != userLogin){
            return true;
        }else{
            return false;
        }
    }
}
