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
import in.pharmeasy.assignment.model.PatientPrescriptionRequest;
import in.pharmeasy.assignment.model.PatientPrescriptionRequest.Authorization;
 
@Repository
public class PatientPrescriptionRequestRepositoryImpl extends AbstractRepositoryImpl<PatientPrescriptionRequest,PatientPrescriptionRequest> implements  PatientPrescriptionRequestRepository {
	
	@PersistenceContext
	EntityManager entityManager;

	@Override
	public EntityManager getEntityManager() {
		return entityManager;
	}

	@Override
	public Class<PatientPrescriptionRequest> getClassType() {
		return PatientPrescriptionRequest.class;
	}

	@Override
	protected Predicate[] getSearchPredicates(Root<PatientPrescriptionRequest> root, PatientPrescriptionRequest example) {
		List<Predicate> predicates= new ArrayList<Predicate>();
		if(example.getCreatedBy()!=null)
		{
			Join<PatientPrescriptionRequest,PatientPrescription> join=root.join("patientPrescription");
			Path<Long> p=join.get("userId");
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			predicates.add(cb.equal(p, example.getCreatedBy()));
		}
		
		if(example.getAuthorization()!=null)
		{
			Path<Authorization> p=root.get("authorization");
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			predicates.add(cb.equal(p, example.getAuthorization()));
		}
		return predicates.toArray(new Predicate[predicates.size()]);
		
	}
	
	

	
}
