package com.ez.portal.core.response;

import javax.xml.bind.annotation.XmlRootElement;

import com.ez.portal.core.entity.User;

/**
 * @author azaz.akhtar
 *
 */
@XmlRootElement(name = "loginResponse")
public class LoginResponse extends AbstractResponse {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private String authenticationToken;
	
	/**
	 * 
	 */
	private User user;

	/**
	 * 
	 */
	public LoginResponse() {
		super();
	}

	/**
	 * @param message
	 * @param status
	 */
	public LoginResponse(String message, Boolean status) {
		super();
		setMessage(message);
		setStatus(status);
	}

	@Override
	public void resetResponse() {
		this.authenticationToken = null;
		super.resetResponse();
	}

	/**
	 * @return
	 */
	public String getAuthenticationToken() {
		return authenticationToken;
	}

	/**
	 * @param authenticationToken
	 */
	public void setAuthenticationToken(String authenticationToken) {
		this.authenticationToken = authenticationToken;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
