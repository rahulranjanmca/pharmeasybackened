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
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "permission")
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Permission extends AbstractEntity{
	
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
	
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "permission_id", insertable = false, updatable = false)
	List<RolePermissionJoin> rolePermissionJoins= new ArrayList<>();
	
	
	@Transient 
	Long userId;
	


	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public List<RolePermissionJoin> getRolePermissionJoins() {
		return rolePermissionJoins;
	}

	public void setRolePermissionJoins(List<RolePermissionJoin> rolePermissionJoins) {
		this.rolePermissionJoins = rolePermissionJoins;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
		
	
	

}
