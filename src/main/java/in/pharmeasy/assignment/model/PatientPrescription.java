package in.pharmeasy.assignment.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
@Table(name = "patient_prescription")
public class PatientPrescription extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	Long id;

	@Column(name = "prescription")
	@NotNull
	String prescription;

	@Column(name = "user_id")
	@NotNull
	Long userId;

	@Column(name = "doctor_id")
	@NotNull
	Long doctorId;
	

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
	

	@Transient
	@JsonSerialize
	@JsonDeserialize
	String email;
	
	

	@Transient
	@JsonSerialize
	@JsonDeserialize
	String type;

	@ManyToOne
	@JoinColumn(name = "user_id", insertable = false, updatable = false)
	User user;

	@ManyToOne
	@JoinColumn(name = "doctor_id", insertable = false, updatable = false)
	User doctor;

	@OneToMany
	@JoinColumn(name = "prescription_id", insertable = false, updatable = false)
	List<PatientPrescriptionAuthorization> patientPrescriptionAuthorizations = new ArrayList<PatientPrescriptionAuthorization>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPrescription() {
		return prescription;
	}

	public void setPrescription(String prescription) {
		this.prescription = prescription;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Long getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(Long doctorId) {
		this.doctorId = doctorId;
	}

	public User getDoctor() {
		return doctor;
	}

	public void setDoctor(User doctor) {
		this.doctor = doctor;
	}

	public List<PatientPrescriptionAuthorization> getPatientPrescriptionAuthorizations() {
		return patientPrescriptionAuthorizations;
	}

	public void setPatientPrescriptionAuthorizations(
			List<PatientPrescriptionAuthorization> patientPrescriptionAuthorizations) {
		this.patientPrescriptionAuthorizations = patientPrescriptionAuthorizations;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public String getDoctorLastName() {
		return doctorLastName;
	}

	public void setDoctorLastName(String doctorLastName) {
		this.doctorLastName = doctorLastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
	
	

}
