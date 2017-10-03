package in.pharmeasy.assignment.rest;

import java.security.Principal;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import in.pharmeasy.assignment.model.User;
import in.pharmeasy.assignment.service.GenericService;
import in.pharmeasy.assignment.service.UserService;

@RestController
@RequestMapping("users")
public class UserRest extends GenericRest<User, User> {

	@Autowired
	UserService userService;

	@Override
	public GenericService<User, User> getService() {
		return userService;
	}

	@RequestMapping(value = "permissions", method = RequestMethod.GET)
	public @ResponseBody Set<String> get(Principal principal) {
		return userService.getPermissionsById(new Long(principal.getName()));
	}

	@RequestMapping(value = "/me", method = RequestMethod.GET)
	public @ResponseBody User getMe(Principal principal) {
		return getService().get(new Long(principal.getName()));
	}

}
