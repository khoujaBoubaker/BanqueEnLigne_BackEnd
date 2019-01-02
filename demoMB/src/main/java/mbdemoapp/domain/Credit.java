package mbdemoapp.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Credit {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String libelle;
	private double apport;
	private double montantemprunte;
	
	private double montantResteApayer;
	
	
	@OneToMany(fetch=FetchType.EAGER,mappedBy="credit",orphanRemoval=true)
	private List<Mensualite> mensulaites;
	
	
	
	@Temporal(TemporalType.DATE)
	private Date dateEmprunt;
	
	private int duree;
	
	




	@Override
	public String toString() {
		return "Credit [id=" + id + ", libelle=" + libelle + ", apport=" + apport + ", montantemprunte="
				+ montantemprunte + ", montantResteApayer=" + montantResteApayer + ", mensulaites=" + mensulaites
				+ ", dateEmprunt=" + dateEmprunt + ", duree=" + duree + "]";
	}


	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="code_client")
	private Client client;
	
	
	public Credit() {
		
	}


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


	


	

	public Client getClient() {
		return client;
	}


	public void setClient(Client client) {
		this.client = client;
	}


	public Date getDateEmprunt() {
		return dateEmprunt;
	}


	public void setDateEmprunt(Date dateEmprunt) {
		this.dateEmprunt = dateEmprunt;
	}


	


	

	public double getApport() {
		return apport;
	}


	public void setApport(double apport) {
		this.apport = apport;
	}


	public int getDuree() {
		return duree;
	}


	public void setDuree(int duree) {
		this.duree = duree;
	}


	public double getMontantemprunte() {
		return montantemprunte;
	}


	public void setMontantemprunte(double montantemprunte) {
		this.montantemprunte = montantemprunte;
	}


	public double getMontantResteApayer() {
		return montantResteApayer;
	}


	public void setMontantResteApayer(double montantResteApayer) {
		this.montantResteApayer = montantResteApayer;
	}


	public List<Mensualite> getMensulaites() {
		return mensulaites;
	}


	public void setMensulaites(List<Mensualite> mensulaites) {
		this.mensulaites = mensulaites;
	}

}
