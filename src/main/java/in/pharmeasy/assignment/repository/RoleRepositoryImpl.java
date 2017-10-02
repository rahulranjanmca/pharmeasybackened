package in.pharmeasy.assignment.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import in.pharmeasy.assignment.model.Role;
 
@Repository
public class RoleRepositoryImpl extends AbstractRepositoryImpl<Role,Role> implements  RoleRepository {
	
	@PersistenceContext
	EntityManager entityManager;

	@Override
	public EntityManager getEntityManager() {
		return entityManager;
	}

	@Override
	public Class<Role> getClassType() {
		return Role.class;
	}


}
