package com.ez.portal.core.dao.intf;

import java.util.List;

import com.ez.portal.core.entity.Password;
import com.ez.portal.core.entity.User;
import com.ez.portal.core.entity.UserInfo;

public interface UserDAO extends CommonDAO<User, Long> {

    User getUserByEmailId(String emailId) throws Exception;
    
    User createUser(User user, Password password) throws Exception;
    
    User createUser(User user, Password password, List<UserInfo> userInfos) throws Exception;
    
    User getUserByAuthenticationToken(String authenticationToken) throws Exception;
    
    List<User> getUsersByEmailIdInAllUserSpaces(String emailId) throws Exception;

    User getSuperUserByEmailId(String emailId) throws Exception;
        
}
