package com.ez.portal.core.response;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.ez.portal.core.entity.UserSpace;

@XmlRootElement(name="userSpaceResponse")
public class UserSpaceResponse extends AbstractResponse {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 */
	private List<UserSpace> userSpaces;

	/**
	 * @return the userSpaces
	 */
	public List<UserSpace> getUserSpaces() {
		return userSpaces;
	}

	/**
	 * @param userSpaces the userSpaces to set
	 */
	public void setUserSpaces(List<UserSpace> userSpaces) {
		this.userSpaces = userSpaces;
	}
	
	@Override
	public void resetResponse() {
		this.userSpaces = null;
		super.resetResponse();
	}
	
}
