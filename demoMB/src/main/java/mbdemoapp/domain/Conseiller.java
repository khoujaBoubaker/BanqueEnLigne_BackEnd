package mbdemoapp.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Conseiller implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public Agence getAgence() {
		return agence;
	}


	public List<RendezVous> getRendezVous() {
		return rendezVous;
	}


	public void setRendezVous(List<RendezVous> rendezVous) {
		this.rendezVous = rendezVous;
	}


	public void setAgence(Agence agence) {
		this.agence = agence;
	}
	@Id
	@GeneratedValue
	private int id;
	


	
	private String nom;
	private String prenom;
	private String email;
	
	
	
	public Conseiller(String nom, String prenom, String email) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
	}
	@Temporal(TemporalType.DATE)
	private Date dateEmbauche;
	
	@Temporal(TemporalType.DATE)
	private Date datedenaissance;
	
	
	@ManyToOne(fetch=FetchType.EAGER)
	private Agence agence;
	
	@JsonIgnore
	@OneToMany(fetch=FetchType.EAGER,mappedBy="conseiller")
	private List<RendezVous> rendezVous;
	
	
	public Date getDateEmbauche() {
		return dateEmbauche;
	}


	public void setDateEmbauche(Date dateEmbauche) {
		this.dateEmbauche = dateEmbauche;
	}


	public Date getDatedenaissance() {
		return datedenaissance;
	}


	public void setDatedenaissance(Date datedenaissance) {
		this.datedenaissance = datedenaissance;
	}


	public Conseiller() {
		
	}
	
	
	public Conseiller(String nom, String prenom, String email, Date dateEmbauche, Date datedenaissance) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.dateEmbauche = dateEmbauche;
		this.datedenaissance = datedenaissance;
	}
	
	
	@Override
	public String toString() {
		return "Conseiller [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", dateEmbauche="
				+ dateEmbauche + ", datedenaissance=" + datedenaissance + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

}
