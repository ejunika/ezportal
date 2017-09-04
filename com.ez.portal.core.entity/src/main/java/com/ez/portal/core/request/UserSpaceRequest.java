package com.ez.portal.core.request;

import com.ez.portal.core.entity.UserSpace;

public class UserSpaceRequest extends AbstractRequest {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 */
	private String emailId;
	
	/**
	 * 
	 */
	private UserSpace userSpace;

	/**
	 * @return the emailId
	 */
	public String getEmailId() {
		return emailId;
	}

	/**
	 * @param emailId the emailId to set
	 */
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	/**
	 * @return the userSpace
	 */
	public UserSpace getUserSpace() {
		return userSpace;
	}

	/**
	 * @param userSpace the userSpace to set
	 */
	public void setUserSpace(UserSpace userSpace) {
		this.userSpace = userSpace;
	}

}
