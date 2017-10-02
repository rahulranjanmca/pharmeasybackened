package in.pharmeasy.assignment.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.pharmeasy.assignment.model.PatientPrescriptionAuthorization;
import in.pharmeasy.assignment.model.PatientPrescriptionRequest;
import in.pharmeasy.assignment.repository.PatientPrescriptionAuthorizationRepository;
import in.pharmeasy.assignment.repository.PatientPrescriptionRequestRepository;

@Service
@Transactional
public class PatientPrescriptionAuthorizationServiceImpl
		extends GenericServiceImpl<PatientPrescriptionAuthorization, PatientPrescriptionAuthorization>
		implements PatientPrescriptionAuthorizationService {

	@Autowired
	PatientPrescriptionAuthorizationRepository patientPrescriptionAuthorizationRepository;

	@Autowired
	PatientPrescriptionRequestRepository patientPrescriptionRequestRepository;

	@Override
	public in.pharmeasy.assignment.repository.AbstractRepository<PatientPrescriptionAuthorization, PatientPrescriptionAuthorization> getDAO() {
		return patientPrescriptionAuthorizationRepository;
	}

	@Override
	public void authorizePrescription(PatientPrescriptionRequest prescriptionRequest,
			PatientPrescriptionAuthorization prescriptionAuthorization) {
		patientPrescriptionAuthorizationRepository.save(prescriptionAuthorization);
		patientPrescriptionRequestRepository.update(prescriptionRequest);

	}

}
