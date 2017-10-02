package in.pharmeasy.assignment.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import in.pharmeasy.assignment.model.RolePermissionJoin;
 
@Repository
public class RolePermissionJoinRepositoryImpl extends AbstractRepositoryImpl<RolePermissionJoin,RolePermissionJoin> implements  RolePermissionJoinRepository {
	
	@PersistenceContext
	EntityManager entityManager;

	@Override
	public EntityManager getEntityManager() {
		return entityManager;
	}

	@Override
	public Class<RolePermissionJoin> getClassType() {
		return RolePermissionJoin.class;
	}



}
