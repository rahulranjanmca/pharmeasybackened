package in.pharmeasy.assignment.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import in.pharmeasy.assignment.model.AuthClient;
 
@Repository
public class AuthClientRepositoryImpl extends AbstractRepositoryImpl<AuthClient,AuthClient> implements  AuthClientRepository {
	
	@PersistenceContext
	EntityManager entityManager;

	

	@Override
	public EntityManager getEntityManager() {
		return entityManager;
	}

	@Override
	public Class<AuthClient> getClassType() {
		return AuthClient.class;
	}

	

}
