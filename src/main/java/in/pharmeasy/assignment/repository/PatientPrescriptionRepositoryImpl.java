package in.pharmeasy.assignment.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import in.pharmeasy.assignment.model.PatientPrescription;
import in.pharmeasy.assignment.model.PatientPrescriptionAuthorization;
import in.pharmeasy.assignment.model.PatientPrescriptionRequest.Authorization;
import in.pharmeasy.assignment.model.User;
 
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
			Join<PatientPrescription,PatientPrescriptionAuthorization> userRoleJoin=root.join("patientPrescriptionAuthorizations",JoinType.LEFT);
			Path<Long> path= userRoleJoin.get("userId");
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			predicates.add(cb.or(cb.isNull(path),cb.notEqual(path, example.getCreatedBy()) ));
			
			/*This is done, so that one you send the request, It should be visible in you list, Again once patient rejects it the recode will get displayed here to send the request again*/
			Predicate criteria = cb.conjunction();
			Join<?, ?> ppr = root.join("patientPrescriptionRequests", JoinType.LEFT);
			criteria = cb.and(criteria, cb.equal(ppr.get("userId"), example.getCreatedBy()), cb.or(cb.equal(ppr.get("authorization"),Authorization.APPROVED),cb.equal(ppr.get("authorization"),Authorization.PENDING)));
			ppr.on(criteria);
			Path<Authorization> authorization=ppr.get("authorization");
			predicates.add(cb.isNull(authorization));
			
		}
		if(example.getDoctorEmail()!=null && !example.getDoctorEmail().isEmpty())
		{
			Join<PatientPrescription,User> join=root.join("doctor");
			Path<String> p=join.get("email");
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			predicates.add(cb.equal(p, example.getDoctorEmail()));
		}
		
		if(example.getPatientEmail()!=null && !example.getPatientEmail().isEmpty())
		{
			Join<PatientPrescription,User> join=root.join("user");
			Path<String> p=join.get("email");
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			predicates.add(cb.equal(p, example.getPatientEmail()));
		}
		return predicates.toArray(new Predicate[predicates.size()]);
		
	}
	
	

	
}
