package in.pharmeasy.assignment.service;

import in.pharmeasy.assignment.model.PatientPrescriptionAuthorization;
import in.pharmeasy.assignment.model.PatientPrescriptionRequest;

public interface PatientPrescriptionAuthorizationService extends GenericService<PatientPrescriptionAuthorization, PatientPrescriptionAuthorization> {
	
	void authorizePrescription(PatientPrescriptionRequest prescriptionRequest, PatientPrescriptionAuthorization prescriptionAuthorization);

}
