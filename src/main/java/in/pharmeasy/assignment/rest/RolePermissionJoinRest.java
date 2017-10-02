package in.pharmeasy.assignment.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.pharmeasy.assignment.model.RolePermissionJoin;
import in.pharmeasy.assignment.service.GenericService;
import in.pharmeasy.assignment.service.RolePermissionJoinService;

@RestController
@RequestMapping("role-permissions")
public class RolePermissionJoinRest extends GenericRest<RolePermissionJoin, RolePermissionJoin> {
	
	@Autowired
	RolePermissionJoinService authClientService;

	@Override
	public GenericService<RolePermissionJoin, RolePermissionJoin> getService() {
		return authClientService;
	}



}
