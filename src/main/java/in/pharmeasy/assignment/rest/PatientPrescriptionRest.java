package in.pharmeasy.assignment.rest;

import java.security.Principal;
import java.util.Date;
import java.util.List;

import javax.ws.rs.BadRequestException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import in.pharmeasy.assignment.model.PatientPrescription;
import in.pharmeasy.assignment.model.PatientPrescriptionAuthorization;
import in.pharmeasy.assignment.model.PatientPrescriptionRequest;
import in.pharmeasy.assignment.model.PatientPrescriptionRequest.Authorization;
import in.pharmeasy.assignment.service.GenericService;
import in.pharmeasy.assignment.service.PatientPrescriptionAuthorizationService;
import in.pharmeasy.assignment.service.PatientPrescriptionRequestService;
import in.pharmeasy.assignment.service.PatientPrescriptionService;

@RestController
@RequestMapping("patient-prescriptions")
public class PatientPrescriptionRest extends GenericRest<PatientPrescription, PatientPrescription> {
	
	@Autowired
	PatientPrescriptionService patientPrescriptionService;
	
	@Autowired
	PatientPrescriptionRequestService patientPrescriptionRequestService;
	
	@Autowired
	PatientPrescriptionAuthorizationService patientPrescriptionAuthorizationService;

	@Override
	public GenericService<PatientPrescription, PatientPrescription> getService() {
		return patientPrescriptionService;
	}
	
	@RequestMapping(value="/pendingRequest/{firstResult}/{maxResult}",method = RequestMethod.POST)
	@PreAuthorize("hasAnyAuthority('patient')")
	public @ResponseBody List<PatientPrescriptionRequest> pendingRequest(@RequestBody PatientPrescriptionRequest t, @PathVariable("firstResult") int firstResult,
			@PathVariable("maxResult") int maxResult, Principal principal) {
		t.setCreatedBy(new Long(principal.getName()));
		t.setAuthorization(Authorization.PENDING);
		List<PatientPrescriptionRequest> records = patientPrescriptionRequestService.getListByCriteria(t, firstResult, maxResult);
		for(PatientPrescriptionRequest record:records)
		{
			record.setDoctorFirstName(record.getPatientPrescription().getDoctor().getFirstName());
			record.setDoctorLastName(record.getPatientPrescription().getDoctor().getLastName());
			record.setDoctorEmail(record.getPatientPrescription().getDoctor().getEmail());
			record.setFirstName(record.getUser().getFirstName());
			record.setLastName(record.getUser().getLastName());
			record.setPatientEmail(record.getUser().getEmail());
			record.setPatientPrescription(null);
			record.setUser(null);
		}
		return records;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	@PreAuthorize("hasAnyAuthority('doctor')")
	public @ResponseBody PatientPrescription save(@RequestBody PatientPrescription t, Principal principal) {
		
		t.setCreatedBy(new Long(principal.getName()));
		t.setUpdatedBy(new Long(principal.getName()));
		t.setCreatedDate(new Date());
		t.setUpdatedDate(new Date());
		t.setDoctorId(new Long(principal.getName()));
		t.setUser(null);
		getService().save(t);
		return t;
	}
	
	@RequestMapping(value = "/search/{firstResult}/{maxResult}", method = RequestMethod.POST)
	public @ResponseBody List<PatientPrescription> getListByCriteria(@RequestBody PatientPrescription t, @PathVariable("firstResult") int firstResult,
			@PathVariable("maxResult") int maxResult, Principal principal) {
		t.setCreatedBy(new Long(principal.getName()));
		List<PatientPrescription> records= getService().getListByCriteria(t, firstResult, maxResult);
		if(!"nonauthorized-prescription".equals(t.getType()) &&  !"my-prescription".equals(t.getType()) && !"authorized-prescription".equals(t.getType()))
			throw new BadRequestException("TYPE IS WRONG");
		if("nonauthorized-prescription".equals(t.getType()))
		{
			for(PatientPrescription record:records)
			{
				record.setPrescription(null);
			}
		}
		for(PatientPrescription record:records)
		{
			record.setDoctorFirstName(record.getDoctor().getFirstName());
			record.setDoctorLastName(record.getDoctor().getLastName());
			record.setDoctorEmail(record.getDoctor().getEmail());
			record.setFirstName(record.getUser().getFirstName());
			record.setLastName(record.getUser().getLastName());
			record.setPatientEmail(record.getUser().getEmail());
		}
		return records;
	}
	
	
	
	@PreAuthorize("hasAnyAuthority('doctor','pharmacist')")
	@RequestMapping(value = "/sendRequest/{prescriptionId}", method = RequestMethod.POST)
	public @ResponseBody PatientPrescriptionRequest sendRequest( @PathVariable("prescriptionId") Long prescriptionId, Principal principal) {
		PatientPrescriptionRequest patientPrescriptionRequest=new PatientPrescriptionRequest();
		patientPrescriptionRequest.setPrescriptionId(prescriptionId);
		patientPrescriptionRequest.setUserId(new Long(principal.getName()));
		patientPrescriptionRequest.setAuthorization(Authorization.PENDING);
		patientPrescriptionRequest.setCreatedBy(new Long(principal.getName()));
		patientPrescriptionRequest.setUpdatedBy(new Long(principal.getName()));
		patientPrescriptionRequest.setCreatedDate(new Date());
		patientPrescriptionRequest.setUpdatedDate(new Date());
		patientPrescriptionRequestService.save(patientPrescriptionRequest);
		return patientPrescriptionRequest;
	}
	
	@PreAuthorize("hasAnyAuthority('patient')")
	@RequestMapping(value = "/approveRequest/{prescriptionRequestId}", method = RequestMethod.POST)
	public @ResponseBody PatientPrescriptionAuthorization approveRequest( @PathVariable("prescriptionRequestId") Long prescriptionRequestId, Principal principal) {
		PatientPrescriptionRequest prescriptionRequest=patientPrescriptionRequestService.get(prescriptionRequestId);
		PatientPrescription patientPrescription= patientPrescriptionService.get(prescriptionRequest.getPrescriptionId());
		
		if(!new Long(principal.getName()).equals(patientPrescription.getUserId()))
		{
			throw new BadRequestException("YOU ARE NOT AUTHORIZED");
		}
		PatientPrescriptionAuthorization patientPrescriptionAuthorization=new PatientPrescriptionAuthorization();
		patientPrescriptionAuthorization.setPrescriptionId(prescriptionRequest.getPrescriptionId());
		patientPrescriptionAuthorization.setUserId(new Long(principal.getName()));
		patientPrescriptionAuthorization.setCreatedBy(new Long(principal.getName()));
		patientPrescriptionAuthorization.setUpdatedBy(new Long(principal.getName()));
		patientPrescriptionAuthorization.setCreatedDate(new Date());
		patientPrescriptionAuthorization.setUpdatedDate(new Date());
		prescriptionRequest.setAuthorization(Authorization.APPROVED);
		patientPrescriptionAuthorizationService.authorizePrescription(prescriptionRequest, patientPrescriptionAuthorization);
		return patientPrescriptionAuthorization;
	}
	
	@PreAuthorize("hasAnyAuthority('patient')")
	@RequestMapping(value = "/rejectRequest/{prescriptionRequestId}", method = RequestMethod.POST)
	public @ResponseBody PatientPrescriptionRequest rejectRequest( @PathVariable("prescriptionRequestId") Long prescriptionRequestId, Principal principal) {
		PatientPrescriptionRequest prescriptionRequest=patientPrescriptionRequestService.get(prescriptionRequestId);
		PatientPrescription patientPrescription= patientPrescriptionService.get(prescriptionRequest.getPrescriptionId());
		
		if(!new Long(principal.getName()).equals(patientPrescription.getUserId()))
		{
			throw new BadRequestException("YOU ARE NOT AUTHORIZED");
		}
		prescriptionRequest.setAuthorization(Authorization.REJECTED);
		patientPrescriptionRequestService.save(prescriptionRequest);
		return prescriptionRequest;
	}

	
	
}
	
	
