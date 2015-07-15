package com.ibm.pricesimulator.service;

import org.springframework.stereotype.Service;

import com.ibm.swat.password.ReturnCode;
import com.ibm.swat.password.cwa2;

@Service
public class UserService {

	private static final String BLUE_GROUPS_AUTH_SERVER = "bluepages.ibm.com";
	/*private static final String BLUE_GROUP_AUTHORIZED = "SHPS_USER";*/
	private static final String BLUE_GROUP_AUTHORIZED = "DCCTAdmin";
	
	
	public void doAuthentication(String userId, String password) throws Exception {

		cwa2 blueGroupsCWA = new cwa2(BLUE_GROUPS_AUTH_SERVER, BLUE_GROUPS_AUTH_SERVER);

		ReturnCode returnCode = blueGroupsCWA.authenticate(userId, password);

		boolean authenticated = returnCode.getCode()==0;

		//Authenticate
		if (!authenticated) {
			throw new Exception("Login failed");
		} else { // If success check if present in the group
			returnCode = blueGroupsCWA.inAGroup(userId, BLUE_GROUP_AUTHORIZED);
			if (returnCode.getCode() != 0) {
				throw new Exception("You don't have enough privileges to access this page!");	
			}
		}
	}
	
}
