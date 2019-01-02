package mbdemoapp.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Role {
	
	
	@Id
	@GeneratedValue
	private int id;
	private String roleName;
	
	
	public Role(int id, String roleName) {
		super();
		this.id = id;
		this.roleName = roleName;
	}
	public Role() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	

}
