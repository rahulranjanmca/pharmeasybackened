package in.pharmeasy.assignment.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import in.pharmeasy.assignment.model.UserRoleJoin;
 
@Repository
public class UserRoleJoinRepositoryImpl extends AbstractRepositoryImpl<UserRoleJoin,UserRoleJoin> implements  UserRoleJoinRepository {
	
	@PersistenceContext
	EntityManager entityManager;

	@Override
	public EntityManager getEntityManager() {
		return entityManager;
	}

	@Override
	public Class<UserRoleJoin> getClassType() {
		return UserRoleJoin.class;
	}

	
}
