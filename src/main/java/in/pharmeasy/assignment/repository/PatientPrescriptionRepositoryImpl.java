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

import in.pharmeasy.assignment.model.PatientPrescription;
import in.pharmeasy.assignment.model.PatientPrescriptionAuthorization;
import in.pharmeasy.assignment.model.Role;
import in.pharmeasy.assignment.model.User;
import in.pharmeasy.assignment.model.UserRoleJoin;
 
@Repository
public class PatientPrescriptionRepositoryImpl extends AbstractRepositoryImpl<PatientPrescription,PatientPrescription> implements  PatientPrescriptionRepository {
	
	@PersistenceContext
	EntityManager entityManager;

	@Override
	public EntityManager getEntityManager() {
		return entityManager;
	}

	@Override
	public Class<PatientPrescription> getClassType() {
		return PatientPrescription.class;
	}

	@Override
	protected Predicate[] getSearchPredicates(Root<PatientPrescription> root, PatientPrescription example) {
		List<Predicate> predicates= new ArrayList<Predicate>();
		
		if("my-prescription".equals(example.getType()))
		{
			Path<Long> p=root.get("userId");
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			predicates.add(cb.equal(p, example.getCreatedBy()));
		}
		if("authorized-prescription".equals(example.getType()))
		{
			Join<PatientPrescription,PatientPrescriptionAuthorization> userRoleJoin=root.join("patientPrescriptionAuthorizations");
			Path<Long> path= userRoleJoin.get("userId");
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			predicates.add(cb.equal(path, example.getCreatedBy()));
		}
		
		if("nonauthorized-prescription".equals(example.getType()))
		{
			Join<PatientPrescription,PatientPrescriptionAuthorization> userRoleJoin=root.join("patientPrescriptionAuthorizations");
			Path<Long> path= userRoleJoin.get("userId");
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			predicates.add(cb.notEqual(path, example.getCreatedBy()));
		}
		/*if(example.getUserId()!=null)
		{
			Path<Long> p=root.get("approvalWorkflowId");
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			//predicates.add(cb.equal(p, example.getApprovalWorkflowId()));
		}*/
		return predicates.toArray(new Predicate[predicates.size()]);
		
	}
	
	

	
}
