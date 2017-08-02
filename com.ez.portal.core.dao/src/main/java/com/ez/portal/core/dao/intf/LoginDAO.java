package com.ez.portal.core.dao.intf;

import java.util.List;

import com.ez.portal.core.entity.Password;
import com.ez.portal.core.entity.PortalSession;
import com.ez.portal.core.entity.User;
import com.ez.portal.core.entity.UserInfo;
import com.ez.portal.core.util.dao.intf.CommonDAO;

public interface LoginDAO extends CommonDAO<User, Long> {
    User getUserInfoByEmailId(String emailId) throws Exception;
    List<User> getUsersByEmailId(String emailId) throws Exception;
    Password getActivePassword(User user) throws Exception;
    Password getActivePasswordForSuperUser(User user) throws Exception;
    Boolean createSession(PortalSession portalSession) throws Exception;
    Boolean makeSessionOut(Long portalSessionId) throws Exception;
    User getUserByAuthenticationToken(String authenticationToken) throws Exception;
    Boolean makePortalSessionInActive(String authenticationToken) throws Exception;
    User getSuperUserByEmailId(String emailId) throws Exception;
    Boolean createSuperUserSession(PortalSession portalSession) throws Exception;
    Boolean createUser(User user, Password password) throws Exception;
    Boolean createUser(User user, Password password, List<UserInfo> userInfos) throws Exception;
}
