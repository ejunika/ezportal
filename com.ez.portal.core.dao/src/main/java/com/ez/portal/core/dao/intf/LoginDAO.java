package com.ez.portal.core.dao.intf;

import com.ez.portal.core.entity.User;

public interface LoginDAO {
    User getUserInfoByEmailId(String emailId);
}
