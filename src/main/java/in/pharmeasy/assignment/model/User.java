package in.pharmeasy.assignment.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
@Table(name="user", uniqueConstraints = { @UniqueConstraint(columnNames = {  "email" }), @UniqueConstraint(columnNames = { "username" }) })
public class User extends AbstractEntity implements UserDetails{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	Long id;
	
	
	@Column(name = "first_name")
	String firstName;

	@Column(name = "last_name")
	String lastName;
	

	@Column(name = "username")
	protected String username;

	@Column(name = "email")
	String email; 

	@Column(name = "locked")
	boolean locked = false;

	@Column(name = "enabled")
	protected boolean enabled = false;

	@Column(name = "password_expired")
	boolean passwordExpired = false;

	@Column(name = "account_expired")
	boolean accountExpired = false;

	@Column(name = "default_role_id")
	Long defaultRoleId;

	@Column(name = "salt")
	String salt;

	@Transient
	String confirmPassword;
	
	@Column(name = "password")
	String password;
	
	@Transient
	@JsonSerialize
	@JsonDeserialize
	String role;

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return !this.isAccountExpired();
	}

	@Override
	public boolean isAccountNonLocked() {
		return !this.isLocked();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return !this.isPasswordExpired();
	}

	@Override
	public boolean isEnabled() {
		return this.enabled;
	}
	
	@OneToMany
	@JoinColumn(name="user_id", insertable=false, updatable=false)
	List<UserRoleJoin> userRoleJoins= new ArrayList<>();


	public List<UserRoleJoin> getUserRoleJoins() {
		return userRoleJoins;
	}

	public void setUserRoleJoins(List<UserRoleJoin> userRoleJoins) {
		this.userRoleJoins = userRoleJoins;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isLocked() {
		return locked;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}

	public boolean isPasswordExpired() {
		return passwordExpired;
	}

	public void setPasswordExpired(boolean passwordExpired) {
		this.passwordExpired = passwordExpired;
	}

	public boolean isAccountExpired() {
		return accountExpired;
	}

	public void setAccountExpired(boolean accountExpired) {
		this.accountExpired = accountExpired;
	}

	public Long getDefaultRoleId() {
		return defaultRoleId;
	}

	public void setDefaultRoleId(Long defaultRoleId) {
		this.defaultRoleId = defaultRoleId;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}


	

}
