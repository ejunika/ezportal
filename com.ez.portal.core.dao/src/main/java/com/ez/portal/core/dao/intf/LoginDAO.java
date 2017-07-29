package com.ez.portal.core.dao.intf;

import java.util.List;

import com.ez.portal.core.entity.Password;
import com.ez.portal.core.entity.PortalSession;
import com.ez.portal.core.entity.User;
import com.ez.portal.core.util.dao.intf.CommonDAO;

public interface LoginDAO extends CommonDAO<User, Long> {
    User getUserInfoByEmailId(String emailId) throws Exception;
    List<User> getUsersByEmailId(String emailId) throws Exception;
    Password getActivePassword(User user) throws Exception;
    Boolean createSession(PortalSession portalSession) throws Exception;
    User getUserByAuthenticationToken(String authenticationToken) throws Exception;
    Boolean makePortalSessionInActive(String authenticationToken) throws Exception;
}
