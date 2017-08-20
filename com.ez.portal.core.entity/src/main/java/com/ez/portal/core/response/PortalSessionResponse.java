package com.ez.portal.core.response;

import javax.xml.bind.annotation.XmlRootElement;

import com.ez.portal.core.entity.User;

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

}
