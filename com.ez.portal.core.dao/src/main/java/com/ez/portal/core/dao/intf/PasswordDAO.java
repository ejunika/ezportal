package com.ez.portal.core.dao.intf;

import com.ez.portal.core.entity.Password;
import com.ez.portal.core.entity.User;

/**
 * @author azaz.akhtar
 *
 */
public interface PasswordDAO extends CommonDAO<Password, Long> {
    
    /**
     * @param user
     * @return
     * @throws Exception
     */
    Password getActivePasswordByUser(User user) throws Exception;

    /**
     * @param user
     * @return
     * @throws Exception
     */
    Password getActivePasswordBySuperUser(User user) throws Exception;
    
}
