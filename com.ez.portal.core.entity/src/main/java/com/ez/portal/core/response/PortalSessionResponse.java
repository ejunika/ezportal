package com.ez.portal.core.response;

import javax.xml.bind.annotation.XmlRootElement;

import com.ez.portal.core.entity.User;
import com.ez.portal.core.entity.UserSpace;

@XmlRootElement(name = "portalSessionResponse")
public class PortalSessionResponse extends AbstractResponse {

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
	private UserSpace userSpace;

	/**
	 * @return the authenticationToken
	 */
	public String getAuthenticationToken() {
		return authenticationToken;
	}

	/**
	 * @param authenticationToken the authenticationToken to set
	 */
	public void setAuthenticationToken(String authenticationToken) {
		this.authenticationToken = authenticationToken;
	}

	/**
	 * @return the users
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param users the users to set
	 */
	public void setUser(User user) {
		this.user = user;
	}
	
	@Override
	public void resetResponse() {
		this.authenticationToken = null;
		this.user = null;
		super.resetResponse();
	}

	public UserSpace getUserSpace() {
		return userSpace;
	}

	public void setUserSpace(UserSpace userSpace) {
		this.userSpace = userSpace;
	}

}
