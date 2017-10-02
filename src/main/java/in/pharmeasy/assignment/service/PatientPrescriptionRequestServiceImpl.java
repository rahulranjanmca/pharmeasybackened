package in.pharmeasy.assignment.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.pharmeasy.assignment.model.PatientPrescriptionRequest;
import in.pharmeasy.assignment.repository.PatientPrescriptionRequestRepository;


@Service
@Transactional
public class PatientPrescriptionRequestServiceImpl extends GenericServiceImpl<PatientPrescriptionRequest, PatientPrescriptionRequest>  implements PatientPrescriptionRequestService{

	@Autowired
	PatientPrescriptionRequestRepository clientRepository;

	@Override
	public in.pharmeasy.assignment.repository.AbstractRepository<PatientPrescriptionRequest, PatientPrescriptionRequest> getDAO() {
		return clientRepository;
	}




	
	
}
