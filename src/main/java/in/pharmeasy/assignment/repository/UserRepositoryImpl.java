package in.pharmeasy.assignment.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import in.pharmeasy.assignment.model.Role;
import in.pharmeasy.assignment.model.User;
import in.pharmeasy.assignment.model.UserRoleJoin;


@Repository
public class UserRepositoryImpl extends AbstractRepositoryImpl<User, User>implements UserRepository {
	
	@PersistenceContext
	EntityManager entityManager;
 
	@Override
	public EntityManager getEntityManager() {
		return entityManager;
	}

	@Override
	public Class<User> getClassType() {
		return User.class;
	}

	@Override
	protected Predicate[] getSearchPredicates(Root<User> root, User example) {
		List<Predicate> predicates= new ArrayList<Predicate>();
		if(example.getRole()!=null && !example.getRole().isEmpty())
		{
			Join<User,UserRoleJoin> userRoleJoin=root.join("userRoleJoins");
			Join<UserRoleJoin,Role> role= userRoleJoin.join("role");
			Path<String> path= role.get("code");
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			predicates.add(cb.equal(path, example.getRole()));
		}
		return predicates.toArray(new Predicate[predicates.size()]);
	}

	
	
	/*public Object getPermissionsByModuleName( Long clientId, Long userId,String moduleId) {
		
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<PermissionUniversalPermissionJoin> query = cb.createQuery(PermissionUniversalPermissionJoin.class);
		Root<PermissionUniversalPermissionJoin> root = query.from(PermissionUniversalPermissionJoin.class);
		query.distinct(true);
		Join<?, ?> permission = (Join<?, ?>) root.fetch("permission", JoinType.INNER);
		Join<?, ?> eavAttributeGroups = (Join<?, ?>) root.fetch("eavAttributeGroups", JoinType.LEFT);
		Join<?, ?> eavEntityAttributes = (Join<?, ?>) eavAttributeGroups.fetch("eavEntityAttributes", JoinType.LEFT);
		Join<?, ?> eavAttribute = (Join<?, ?>) eavEntityAttributes.fetch("eavAttribute", JoinType.LEFT);
		eavAttribute.fetch("eavOptions", JoinType.LEFT);
		
		//query.where(cb.and(cb.equal(root.get("code"), attributeSetCode),cb.equal(entityType.get("code"), entityCode),cb.equal(root.get("clientId"), clientId)));
		
		//ENTITY entity = getEntityManager().createQuery(query).getSingleResult();
		//List<ENTITY> entities = getEntityManager().createQuery(query).getResultList();
		//return map(entity, getDTOClazz());
		return User.class;
	}*/



}
