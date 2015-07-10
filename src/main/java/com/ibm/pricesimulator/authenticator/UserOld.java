package com.ibm.pricesimulator.authenticator;

import java.io.Serializable;

public class UserOld implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String userId;
	
	
	public UserOld() {
		
	}
	
	public UserOld(String userId) {
		super();
		this.userId = userId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + "]";
	}	
}
