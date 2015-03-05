package org.kuali.kra.test.helpers;

import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.rice.kim.impl.role.RolePermissionBo;
import org.kuali.rice.krad.service.BusinessObjectService;

public class RolePermissionTestHelper extends TestHelper {

	public RolePermissionBo createRolePermission(String permissionId, String roleId) {
		RolePermissionBo rolePermission = new RolePermissionBo();
		rolePermission = buildRolePermission(permissionId, roleId);		
		return KraServiceLocator.getService(BusinessObjectService.class).save(rolePermission);
	}
	
	private RolePermissionBo buildRolePermission(String permissionId, String roleId) {
		RolePermissionBo rolePermission = new RolePermissionBo();
		rolePermission.setId("1");
		rolePermission.setRoleId(roleId);
		rolePermission.setPermissionId(permissionId);
		rolePermission.setActive(true);
		return rolePermission;
	}
	
}
