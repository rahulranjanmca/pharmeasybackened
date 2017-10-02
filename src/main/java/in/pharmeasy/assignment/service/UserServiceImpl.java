package in.pharmeasy.assignment.service;

import java.util.HashSet;
import java.util.Set;

import javax.transaction.Transactional;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.pharmeasy.assignment.model.RolePermissionJoin;
import in.pharmeasy.assignment.model.User;
import in.pharmeasy.assignment.model.UserRoleJoin;
import in.pharmeasy.assignment.repository.AbstractRepository;
import in.pharmeasy.assignment.repository.RoleRepository;
import in.pharmeasy.assignment.repository.UserRepository;
import in.pharmeasy.assignment.repository.UserRoleJoinRepository;

@Service
@Transactional
public class UserServiceImpl extends GenericServiceImpl<User, User> implements UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserRoleJoinRepository userRoleJoinRepository;

	@Autowired
	RoleRepository roleRepository;

	@Override
	public void save(User t) {
		if (t.getPassword() == null || t.getPassword().isEmpty()) {
			t.setPassword(RandomStringUtils.randomAlphanumeric(8).toUpperCase());
		}
		if (t.getUsername() == null || t.getUsername().isEmpty()) {
			t.setUsername(RandomStringUtils.randomAlphanumeric(30).toLowerCase());
		}
		super.save(t);
	}

	@Override
	public AbstractRepository<User, User> getDAO() {
		return userRepository;
	}
	
	@Override
	public Set<String> getPermissionsById(Long id) {
		Set<String> permissions = new HashSet<String>();
		User user = userRepository.get(id);
		for (UserRoleJoin userRoleJoin : user.getUserRoleJoins()) {
			for (RolePermissionJoin rolePermissionJoin : userRoleJoin.getRole().getRolePermissionJoins()) {
				permissions.add(rolePermissionJoin.getPermission().getCode());
			}
		}
		return permissions;
	}
}
