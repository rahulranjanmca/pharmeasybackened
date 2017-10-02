package in.pharmeasy.assignment.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.pharmeasy.assignment.model.UserRoleJoin;
import in.pharmeasy.assignment.repository.UserRoleJoinRepository;


@Service
@Transactional
public class UserRoleJoinServiceImpl extends GenericServiceImpl<UserRoleJoin, UserRoleJoin>  implements UserRoleJoinService{

	@Autowired
	UserRoleJoinRepository clientRepository;

	@Override
	public in.pharmeasy.assignment.repository.AbstractRepository<UserRoleJoin, UserRoleJoin> getDAO() {
		return clientRepository;
	}



	
	
	
}
