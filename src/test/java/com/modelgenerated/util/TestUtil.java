package com.modelgenerated.util;

import com.modelgenerated.authentication.AuthenticationService;
import com.modelgenerated.authentication.AuthenticationServiceBean;
import com.modelgenerated.authentication.Tenant;
import com.modelgenerated.foundation.dataaccess.UserContext;
import com.modelgenerated.foundation.identity.Identity;
import com.modelgenerated.foundation.identity.IdentityBuilder;
import com.modelgenerated.foundation.logging.Logger;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class TestUtil {

	@Test
	public static UserContext createTenant(String userName, String tenantName) {
		AuthenticationService authenticationService = new AuthenticationServiceBean();
		Tenant tenant = authenticationService.newTenant(new UserContext(null, null, null));

		Identity accountId = IdentityBuilder.createIdentity();
		UserContext userContext = new UserContext(userName, accountId, tenant);

		return userContext;
	}
	
}
