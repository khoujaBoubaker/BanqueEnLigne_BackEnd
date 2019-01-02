package mbdemoapp.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Agence implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idagence;
	private String addresse;
	private String commune;
	private String telephone;
	
	@JsonIgnore
	@OneToMany(fetch=FetchType.LAZY,mappedBy="agence")
	List<Operation> operations;
	
	public List<Conseiller> getConseillers() {
		return conseillers;
	}











	public void setConseillers(List<Conseiller> conseillers) {
		this.conseillers = conseillers;
	}











	public List<Client> getClients() {
		return clients;
	}











	public void setClients(List<Client> clients) {
		this.clients = clients;
	}











	@JsonIgnore
	@OneToMany(fetch=FetchType.EAGER,mappedBy="agence")
	List<Conseiller> conseillers;
	
	@JsonIgnore
	@OneToMany(fetch=FetchType.LAZY,mappedBy="agence")
	List<Client> clients;
	
	
	












	public List<Operation> getOperations() {
		return operations;
	}











	public void setOperations(List<Operation> operations) {
		this.operations = operations;
	}











	public Agence(String addresse, String commune, String telephone) {
		super();
		this.addresse = addresse;
		this.commune = commune;
		this.telephone = telephone;
	}











	public Agence() {
		
	}
	
	
	
	
	
	
	
	
	
	

	public Agence(int idagence, String addresse, String commune) {
		super();
		this.idagence = idagence;
		this.addresse = addresse;
		this.commune = commune;
	}











	// Methode ToString
	
	
	@Override
	public String toString() {
		return "Agence [idagence=" + idagence + ", addresse=" + addresse + ", commune=" + commune + "]";
	}
	public int getIdagence() {
		return idagence;
	}
	public void setIdagence(int idagence) {
		this.idagence = idagence;
	}
	public String getAddresse() {
		return addresse;
	}
	public void setAddresse(String addresse) {
		this.addresse = addresse;
	}
	public String getCommune() {
		return commune;
	}
	public void setCommune(String commune) {
		this.commune = commune;
	}











	public String getTelephone() {
		return telephone;
	}











	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

}
