package in.pharmeasy.assignment.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.pharmeasy.assignment.model.AuthClient;
import in.pharmeasy.assignment.repository.AbstractRepository;
import in.pharmeasy.assignment.repository.AuthClientRepository;


@Service
@Transactional
public class AuthClientServiceImpl extends GenericServiceImpl<AuthClient, AuthClient>  implements AuthClientService{

	@Autowired
	AuthClientRepository authClientRepository;

	@Override
	public AbstractRepository<AuthClient, AuthClient> getDAO() {
		return authClientRepository;
	}

	

	
	
}
