package com.ez.portal.core.dao.intf;

import java.util.List;

import com.ez.portal.core.entity.Password;
import com.ez.portal.core.entity.User;
import com.ez.portal.core.entity.UserInfo;

/**
 * @author azaz.akhtar
 *
 */
public interface UserDAO extends CommonDAO<User, Long> {

    /**
     * @param emailId
     * @return
     * @throws Exception
     */
    User getUserByEmailId(String emailId) throws Exception;
    
    /**
     * @param emailId
     * @return
     * @throws Exception
     */
    User getUserByEmailId(String emailId, String shardKey) throws Exception;
    
    /**
     * @param user
     * @param password
     * @return
     * @throws Exception
     */
    User createUser(User user, Password password) throws Exception;
    
    /**
     * @param user
     * @param password
     * @param userInfos
     * @return
     * @throws Exception
     */
    User createUser(User user, Password password, List<UserInfo> userInfos) throws Exception;
    
    /**
     * @param user
     * @return
     * @throws Exception
     */
    User createUser(User user) throws Exception;
    
    /**
     * @param authenticationToken
     * @return
     * @throws Exception
     */
    User getUserByAuthenticationToken(String authenticationToken) throws Exception;
    
    /**
     * @param portalSessionToken
     * @return
     * @throws Exception
     */
    User getUser(String portalSessionToken) throws Exception;
    
    /**
     * @param emailId
     * @return
     * @throws Exception
     */
    List<User> getUsersByEmailIdInAllUserSpaces(String emailId) throws Exception;

    /**
     * @param emailId
     * @return
     * @throws Exception
     */
    User getSuperUserByEmailId(String emailId) throws Exception;

	/**
	 * @param emailId
	 * @return
	 * @throws Exception
	 */
	User getActiveUserByEmailId(String emailId) throws Exception;

	/**
	 * @param emailId
	 * @param shardKey
	 * @return
	 * @throws Exception
	 */
	User getActiveUserByEmailId(String emailId, String shardKey) throws Exception;
	
	/**
	 * @return
	 * @throws Exception
	 */
	List<User> getAllPossibleUsers() throws Exception;
	
	/**
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	Boolean unblockUser(Long userId) throws Exception;

	/**
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	Boolean blockUser(Long userId) throws Exception;
        
}
