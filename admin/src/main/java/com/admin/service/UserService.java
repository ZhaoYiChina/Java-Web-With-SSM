package com.admin.service;

import com.admin.entities.User;

import java.util.List;

public interface UserService {
    List<User> getAllUser();

    User load(Integer id);

    Boolean auth(User user);

}
