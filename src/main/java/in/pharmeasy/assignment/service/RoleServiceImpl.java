package in.pharmeasy.assignment.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.pharmeasy.assignment.model.Role;
import in.pharmeasy.assignment.repository.RoleRepository;


@Service
@Transactional
public class RoleServiceImpl extends GenericServiceImpl<Role, Role>  implements RoleService{

	@Autowired
	RoleRepository clientRepository;

	@Override
	public in.pharmeasy.assignment.repository.AbstractRepository<Role, Role> getDAO() {
		return clientRepository;
	}

	
	
}
