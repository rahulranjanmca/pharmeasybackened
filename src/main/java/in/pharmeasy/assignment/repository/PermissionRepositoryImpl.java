package in.pharmeasy.assignment.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import in.pharmeasy.assignment.model.Permission;
 
@Repository
public class PermissionRepositoryImpl extends AbstractRepositoryImpl<Permission,Permission> implements  PermissionRepository {
	
	@PersistenceContext
	EntityManager entityManager;

	@Override
	public EntityManager getEntityManager() {
		return entityManager;
	}

	@Override
	public Class<Permission> getClassType() {
		return Permission.class;
	}

	@Override
	protected Predicate[] getSearchPredicates(Root<Permission> root, Permission example) {
		List<Predicate> predicates= new ArrayList<Predicate>();
		if(example.getUserId()!=null)
		{
			Path<Long> p=root.get("approvalWorkflowId");
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			//predicates.add(cb.equal(p, example.getApprovalWorkflowId()));
		}
		return predicates.toArray(new Predicate[predicates.size()]);
		
	}
	
	

	
}
