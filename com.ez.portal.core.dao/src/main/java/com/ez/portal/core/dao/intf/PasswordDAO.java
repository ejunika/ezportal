package com.ez.portal.core.dao.intf;

import com.ez.portal.core.entity.Password;
import com.ez.portal.core.entity.User;

public interface PasswordDAO extends CommonDAO<Password, Long> {
    
    Password getActivePasswordByUser(User user) throws Exception;

    Password getActivePasswordBySuperUser(User user) throws Exception;
    
}
