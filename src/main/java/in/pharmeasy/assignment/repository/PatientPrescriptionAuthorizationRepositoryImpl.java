package in.pharmeasy.assignment.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import in.pharmeasy.assignment.model.PatientPrescriptionAuthorization;
 
@Repository
public class PatientPrescriptionAuthorizationRepositoryImpl extends AbstractRepositoryImpl<PatientPrescriptionAuthorization,PatientPrescriptionAuthorization> implements  PatientPrescriptionAuthorizationRepository {
	
	@PersistenceContext
	EntityManager entityManager;

	@Override
	public EntityManager getEntityManager() {
		return entityManager;
	}

	@Override
	public Class<PatientPrescriptionAuthorization> getClassType() {
		return PatientPrescriptionAuthorization.class;
	}

	@Override
	protected Predicate[] getSearchPredicates(Root<PatientPrescriptionAuthorization> root, PatientPrescriptionAuthorization example) {
		List<Predicate> predicates= new ArrayList<Predicate>();
		/*if(example.getUserId()!=null)
		{
			Path<Long> p=root.get("approvalWorkflowId");
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			//predicates.add(cb.equal(p, example.getApprovalWorkflowId()));
		}*/
		return predicates.toArray(new Predicate[predicates.size()]);
		
	}
	
	

	
}
