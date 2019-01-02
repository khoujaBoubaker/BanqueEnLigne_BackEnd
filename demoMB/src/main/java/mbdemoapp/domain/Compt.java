package mbdemoapp.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type_compte",length=2)
public abstract class Compt {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idcpt;
	private double solde;
	
	@Temporal(TemporalType.DATE)
	private Date datedecreation;
	
	private String numeroCompte;
	
	public Compt() {}
	
	public List<Operation> getOperations() {
		return operations;
	}

	public Compt(double solde, Date datedecreation, String numeroCompte) {
		super();
		this.solde = solde;
		this.datedecreation = datedecreation;
		this.numeroCompte = numeroCompte;
	}

	public void setOperations(List<Operation> operations) {
		this.operations = operations;
	}

	@OneToMany(fetch=FetchType.EAGER,mappedBy="compte")
	protected List<Operation> operations;
	
	@JsonIgnore
	@ManyToOne(fetch=FetchType.EAGER)
	private Client client;

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public int getIdcpt() {
		return idcpt;
	}

	public void setIdcpt(int idcpt) {
		this.idcpt = idcpt;
	}

	public double getSolde() {
		return solde;
	}

	public void setSolde(double solde) {
		this.solde = solde;
	}

	public Date getDatedecreation() {
		return datedecreation;
	}

	public void setDatedecreation(Date datedecreation) {
		this.datedecreation = datedecreation;
	}

	public String getNumeroCompte() {
		return numeroCompte;
	}

	public void setNumeroCompte(String numeroCompte) {
		this.numeroCompte = numeroCompte;
	}
	
	

}
