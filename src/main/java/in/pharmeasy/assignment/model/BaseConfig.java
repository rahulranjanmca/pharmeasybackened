package in.pharmeasy.assignment.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "base_config")
public class BaseConfig extends AbstractEntity{
	
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */

	@Id
	@Column(name = "id")
	String id;
	
	String context;

	@Column(name = "value")
	String value;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
