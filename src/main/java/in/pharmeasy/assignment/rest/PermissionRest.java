package in.pharmeasy.assignment.rest;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import in.pharmeasy.assignment.model.Permission;
import in.pharmeasy.assignment.service.GenericService;
import in.pharmeasy.assignment.service.PermissionService;

@RestController
@RequestMapping("permissions")
public class PermissionRest extends GenericRest<Permission, Permission> {

	@Autowired
	PermissionService authClientService;

	@Override
	public GenericService<Permission, Permission> getService() {
		return authClientService;
	}

	@RequestMapping(value = "permissions", method = RequestMethod.GET)
	public @ResponseBody List<String> get(Principal principal) {
		List<String> permissionToReturn = new ArrayList<>();
		Permission permissionSearch = new Permission();
		permissionSearch.setUserId(new Long(principal.getName()));
		List<Permission> permissions = getService().getListByCriteria(permissionSearch, -1, 0);
		for (Permission permission : permissions) {
			permissionToReturn.add(permission.getCode());
		}
		return permissionToReturn;
	}

}
