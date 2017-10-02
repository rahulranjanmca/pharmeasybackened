package in.pharmeasy.assignment.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.pharmeasy.assignment.model.Permission;
import in.pharmeasy.assignment.repository.PermissionRepository;


@Service
@Transactional
public class PermissionServiceImpl extends GenericServiceImpl<Permission, Permission>  implements PermissionService{

	@Autowired
	PermissionRepository clientRepository;

	@Override
	public in.pharmeasy.assignment.repository.AbstractRepository<Permission, Permission> getDAO() {
		return clientRepository;
	}




	
	
}
