package in.pharmeasy.assignment.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

@MappedSuperclass
public abstract class AbstractEntity  implements Serializable{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "created_by", updatable = false)
	@Basic
	@NotNull
	Long createdBy;

	@Column(name = "updated_by")
	@Basic
	@NotNull
	Long updatedBy;

	@Column(name = "created_date", updatable = false)
	@Basic
	@NotNull
	Date createdDate;

	@Column(name = "updated_date")
	@Basic
	@NotNull
	Date updatedDate;

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Long getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	
	
	
	
}
