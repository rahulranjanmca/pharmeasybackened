package in.pharmeasy.assignment.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.pharmeasy.assignment.model.PatientPrescription;
import in.pharmeasy.assignment.model.PatientPrescriptionAuthorization;
import in.pharmeasy.assignment.model.PatientPrescriptionRequest.Authorization;
import in.pharmeasy.assignment.repository.AbstractRepository;
import in.pharmeasy.assignment.repository.PatientPrescriptionAuthorizationRepository;
import in.pharmeasy.assignment.repository.PatientPrescriptionRepository;

@Service
@Transactional
public class PatientPrescriptionServiceImpl extends GenericServiceImpl<PatientPrescription, PatientPrescription>
		implements PatientPrescriptionService {

	@Autowired
	PatientPrescriptionRepository patientPrescriptionRepository;

	@Autowired
	PatientPrescriptionAuthorizationRepository patientPrescriptionAuthorizationRepository;

	@Override
	public AbstractRepository<PatientPrescription, PatientPrescription> getDAO() {
		return patientPrescriptionRepository;
	}

	@Override
	public List<PatientPrescription> getListByCriteria(PatientPrescription approval, int firstResult, int maxResult,
			Map<String, Boolean> orderBy, boolean distinct) {
		return super.getListByCriteria(approval, firstResult, maxResult, orderBy, distinct);
	}

	@Override
	public void save(PatientPrescription t) {
		super.save(t);
		PatientPrescriptionAuthorization patientPrescriptionAuthorization = new PatientPrescriptionAuthorization();
		patientPrescriptionAuthorization.setPrescriptionId(t.getId());
		patientPrescriptionAuthorization.setUserId(new Long(t.getDoctorId()));
		patientPrescriptionAuthorization.setCreatedBy(new Long(t.getDoctorId()));
		patientPrescriptionAuthorization.setUpdatedBy(new Long(t.getDoctorId()));
		patientPrescriptionAuthorization.setCreatedDate(new Date());
		patientPrescriptionAuthorization.setUpdatedDate(new Date());
		patientPrescriptionAuthorizationRepository.save(patientPrescriptionAuthorization);

	}

}
