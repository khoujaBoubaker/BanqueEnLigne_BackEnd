package mbdemoapp.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreType;

@Entity
public class Client implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idclient;
	private String nom;
	private String prenom;
	private double salaire;
	private String email;
	
	
	
	






	@Temporal(TemporalType.DATE)
	private Date dateDeCreation;
	
	public Date getDateDeCreation() {
		return dateDeCreation;
	}


	public void setDateDeCreation(Date dateDeCreation) {
		this.dateDeCreation = dateDeCreation;
	}

	@Temporal(TemporalType.DATE)
	private Date datedenaissance;
	
	
	@ManyToOne(fetch=FetchType.EAGER)
	private Agence agence;
	
	
	
	




	public Client(String nom, String prenom, double salaire, String email) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.setSalaire(salaire);
		this.setEmail(email);
	}
	

	public Agence getAgence() {
		return agence;
	}


	public void setAgence(Agence agence) {
		this.agence = agence;
	}


	public Date getdatedenaissance() {
		return datedenaissance;
	}

	public void setdatedenaissance(Date date) {
		this.datedenaissance=date;
	}

	

	

	public Client(String nom, String prenom, Administrateur administrateur) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		
	}
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="client")
	private List<Credit> credits;
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="client")
	private List<Compt> comptes;
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="client")
	private List<RendezVous> rendezVous;

	public Client(String nom, String prenom, double salaire, String email, List<Compt> comptes) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.salaire = salaire;
		this.email = email;
		this.comptes = comptes;
	}

	public Client(String nom, String prenom, double salaire, String email, Date datedenaissance) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.salaire = salaire;
		this.email = email;
		this.datedenaissance = datedenaissance;
	}


	
	



	


	@Override
	public String toString() {
		return "Client [idclient=" + idclient + ", nom=" + nom + ", prenom=" + prenom + ", salaire=" + salaire
				+ ", email=" + email + ", dateDeCreation=" + dateDeCreation
				+ ", datedenaissance=" + datedenaissance + "]";
	}


	public Client() {}

	public int getIdclient() {
		return idclient;
	}

	public List<Compt> getComptes() {
		return comptes;
	}

	public void setComptes(List<Compt> comptes) {
		this.comptes = comptes;
	}

	public void setIdclient(int idclient) {
		this.idclient = idclient;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public double getSalaire() {
		return salaire;
	}

	public void setSalaire(double salaire) {
		this.salaire = salaire;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	

}
