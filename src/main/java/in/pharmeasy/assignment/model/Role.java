package in.pharmeasy.assignment.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "role")
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Role extends AbstractEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	Long id;

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	String code;
	
	String name;
	
	
	@OneToMany
	@JoinColumn(name="role_id", insertable=false, updatable=false)
	List<UserRoleJoin> userRoleJoins= new ArrayList<>();
	

	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "role_id", insertable = false, updatable = false)
	List<RolePermissionJoin> rolePermissionJoins= new ArrayList<>();



	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<UserRoleJoin> getUserRoleJoins() {
		return userRoleJoins;
	}

	public void setUserRoleJoins(List<UserRoleJoin> userRoleJoins) {
		this.userRoleJoins = userRoleJoins;
	}

	public List<RolePermissionJoin> getRolePermissionJoins() {
		return rolePermissionJoins;
	}

	public void setRolePermissionJoins(List<RolePermissionJoin> rolePermissionJoins) {
		this.rolePermissionJoins = rolePermissionJoins;
	}

	
	

}
