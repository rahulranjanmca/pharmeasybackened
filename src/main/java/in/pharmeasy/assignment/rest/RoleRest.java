package in.pharmeasy.assignment.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.pharmeasy.assignment.model.Role;
import in.pharmeasy.assignment.service.GenericService;
import in.pharmeasy.assignment.service.RoleService;

@RestController
@RequestMapping("roles")
public class RoleRest extends GenericRest<Role, Role> {
	
	@Autowired
	RoleService roleRest;

	@Override
	public GenericService<Role, Role> getService() {
		return roleRest;
	}

	
	
}
	
	
