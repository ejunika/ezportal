package com.ez.portal.core.dao.intf;

import java.util.List;

import com.ez.portal.core.entity.User;

public interface LoginDAO {
    User getUserInfoByEmailId(String emailId);
    User getUser(Long userId);
    List<User> getAllUsers();
}
