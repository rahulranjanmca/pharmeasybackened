package in.pharmeasy.assignment.service;

import java.util.Set;

import in.pharmeasy.assignment.model.User;

public interface UserService extends GenericService<User, User>{

	Set<String> getPermissionsById(Long id);

}
