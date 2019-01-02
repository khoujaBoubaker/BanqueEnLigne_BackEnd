package mbdemoapp.domain;

import java.io.Serializable;
import java.util.Date;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cascade;



@Entity
public class RendezVous  {
	
	public Client getClient() {
		return client;
	}


	public void setClient(Client client) {
		this.client = client;
	}


	public Conseiller getConseiller() {
		return conseiller;
	}


	@Override
	public String toString() {
		return "RendezVous [id=" + id + ", libelle=" + libelle + ", dateRendezVous=" + dateRendezVous
				+ ", heureRendezVous=" + heureRendezVous + ", client=" + client + ", conseiller=" + conseiller + "]";
	}


	public void setConseiller(Conseiller conseiller) {
		this.conseiller = conseiller;
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String libelle;
	
	@Temporal(TemporalType.DATE)
	private Date dateRendezVous;
	
	@Temporal(TemporalType.TIME)
	private Date heureRendezVous;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private Client client;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private Conseiller conseiller;
	
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getLibelle() {
		return libelle;
	}


	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}


	public Date getDateRendezVous() {
		return dateRendezVous;
	}


	public void setDateRendezVous(Date dateRendezVous) {
		this.dateRendezVous = dateRendezVous;
	}


	public Date getHeureRendezVous() {
		return heureRendezVous;
	}


	public void setHeureRendezVous(Date heureRendezVous) {
		this.heureRendezVous = heureRendezVous;
	}


	public RendezVous() {
		
	}

}
