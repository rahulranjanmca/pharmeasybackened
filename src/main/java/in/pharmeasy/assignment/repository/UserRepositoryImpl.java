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
		if(example.getEmail()!=null && !example.getEmail().isEmpty())
		{
			Path<String> path= root.get("code");
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			predicates.add(cb.equal(path, example.getEmail()));
		}
		if(example.getFirstName()!=null && !example.getFirstName().isEmpty())
		{
			Path<String> path= root.get("code");
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			predicates.add(cb.like(cb.lower(path), "%"+example.getEmail().toLowerCase()+"%"));
			
		}
		return predicates.toArray(new Predicate[predicates.size()]);
	}

	
	




}
