package in.pharmeasy.assignment.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.pharmeasy.assignment.model.UserRoleJoin;
import in.pharmeasy.assignment.service.GenericService;
import in.pharmeasy.assignment.service.UserRoleJoinService;

@RestController
@RequestMapping("user-roles")
public class UserRoleJoinClientRest extends GenericRest<UserRoleJoin, UserRoleJoin> {
	
	@Autowired
	UserRoleJoinService userRoleJoinService;

	@Override
	public GenericService<UserRoleJoin, UserRoleJoin> getService() {
		return userRoleJoinService;
	}


	
}
