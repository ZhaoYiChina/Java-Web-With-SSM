package com.admin.dao;

import com.admin.entities.User;

import java.util.List;

public interface UserDao {
    List<User> getAllUsers();

    User load(Integer id);

    User auth(User user);
}
