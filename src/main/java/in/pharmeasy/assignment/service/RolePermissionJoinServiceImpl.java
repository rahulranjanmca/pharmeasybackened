package in.pharmeasy.assignment.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.pharmeasy.assignment.model.RolePermissionJoin;
import in.pharmeasy.assignment.repository.RolePermissionJoinRepository;


@Service
@Transactional
public class RolePermissionJoinServiceImpl extends GenericServiceImpl<RolePermissionJoin, RolePermissionJoin>  implements RolePermissionJoinService{

	@Autowired
	RolePermissionJoinRepository clientRepository;

	@Override
	public in.pharmeasy.assignment.repository.AbstractRepository<RolePermissionJoin, RolePermissionJoin> getDAO() {
		return clientRepository;
	}



	
	
}
