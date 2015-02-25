package org.kuali.kra.test.helpers;

import org.kuali.kra.test.fixtures.RoleFixture;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.kim.api.role.RoleService;


/**
 * A class the helps tests create and persist Roles from fixtures.
 */
public class RoleTestHelper extends TestHelper {

	public void addPersonToRole(Person person, RoleFixture role) {
    	getService(RoleService.class).assignPrincipalToRole(
    			person.getPrincipalId(),
    			role.getNamespaceCode(),
    			role.getRoleName(),
    			role.getQualifications());
    }

}
