package in.pharmeasy.assignment.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
@Table(name = "patient_prescription_request")
public class PatientPrescriptionRequest extends AbstractEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	Long id;

	@Column(name="prescription_id")
	@NotNull
	Long prescriptionId;
	
	@ManyToOne
	@JoinColumn(name="prescription_id", insertable=false, updatable=false)
	PatientPrescription patientPrescription;
	
	@Column(name="user_id")
	@NotNull
	Long userId;
	
	@JoinColumn(name="user_id", insertable=false, updatable=false)
	@ManyToOne
	User user;
	
	@Enumerated(EnumType.ORDINAL)
	@Column(name="authorization")
	@NotNull
	Authorization authorization;
	
	@Transient
	@JsonSerialize
	@JsonDeserialize
	String firstName;
	
	@Transient
	@JsonSerialize
	@JsonDeserialize
	String lastName;
	
	
	@Transient
	@JsonSerialize
	@JsonDeserialize
	String doctorFirstName;
	
	@Transient
	@JsonSerialize
	@JsonDeserialize
	String doctorEmail;
	
	@Transient
	@JsonSerialize
	@JsonDeserialize
	String patientEmail;
	
	@Transient
	@JsonSerialize
	@JsonDeserialize
	String doctorLastName;
	


	public enum Authorization {
		 PENDING, APPROVED, REJECTED
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPrescriptionId() {
		return prescriptionId;
	}

	public void setPrescriptionId(Long prescriptionId) {
		this.prescriptionId = prescriptionId;
	}

	public PatientPrescription getPatientPrescription() {
		return patientPrescription;
	}

	public void setPatientPrescription(PatientPrescription patientPrescription) {
		this.patientPrescription = patientPrescription;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Authorization getAuthorization() {
		return authorization;
	}

	public void setAuthorization(Authorization authorization) {
		this.authorization = authorization;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDoctorFirstName() {
		return doctorFirstName;
	}

	public void setDoctorFirstName(String doctorFirstName) {
		this.doctorFirstName = doctorFirstName;
	}

	public String getDoctorEmail() {
		return doctorEmail;
	}

	public void setDoctorEmail(String doctorEmail) {
		this.doctorEmail = doctorEmail;
	}

	public String getPatientEmail() {
		return patientEmail;
	}

	public void setPatientEmail(String patientEmail) {
		this.patientEmail = patientEmail;
	}

	public String getDoctorLastName() {
		return doctorLastName;
	}

	public void setDoctorLastName(String doctorLastName) {
		this.doctorLastName = doctorLastName;
	}

	

}
