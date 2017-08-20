package com.ez.portal.core.rest.service.impl;

import com.ez.portal.core.request.LoginRequest;
import com.ez.portal.core.request.SignUpRequest;
import com.ez.portal.core.response.LoginResponse;
import com.ez.portal.core.response.UserResponse;
import com.ez.portal.core.rest.manager.LoginServiceManager;
import com.ez.portal.core.rest.service.intf.LoginService;

public class LoginServiceImpl implements LoginService {

	private LoginServiceManager loginServiceManager;
	
    /**
	 * @return the loginServiceManager
	 */
	public LoginServiceManager getLoginServiceManager() {
		return loginServiceManager;
	}

	/**
	 * @param loginServiceManager the loginServiceManager to set
	 */
	public void setLoginServiceManager(LoginServiceManager loginServiceManager) {
		this.loginServiceManager = loginServiceManager;
	}

	@Override
    public UserResponse signUp(SignUpRequest signUpRequest) {
        return loginServiceManager.signUp(signUpRequest);
    }

    @Override
    public LoginResponse doLogin(LoginRequest loginRequest) {
        return loginServiceManager.proceedLogin(loginRequest);
    }

    @Override
    public LoginResponse logout(String authenticationToken) throws Exception {
        return loginServiceManager.doLogout(authenticationToken);
    }

}
