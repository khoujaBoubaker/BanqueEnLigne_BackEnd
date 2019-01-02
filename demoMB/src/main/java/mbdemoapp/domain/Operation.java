package mbdemoapp.domain;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type_op",length=2)
public abstract class Operation {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int numero;
	@Temporal(TemporalType.DATE)
	public Date deateoperation;
	
	public double soldeDuJour;
	public double getSoldeDuJour() {
		return soldeDuJour;
	}

	public void setSoldeDuJour(double soldeDuJour) {
		this.soldeDuJour = soldeDuJour;
	}

	public int getNumero() {
		return numero;
	}

	@Override
	public String toString() {
		return "Operation [numero=" + numero + ", deateoperation=" + deateoperation + ", montant=" + montant
				+ ", libelle=" + libelle + "]";
	}

	public Operation(Date deateoperation, double montant, String libelle) {
		super();
		this.deateoperation = deateoperation;
		this.montant = montant;
		this.libelle = libelle;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public Date getDeateoperation() {
		return deateoperation;
	}

	public void setDeateoperation(Date deateoperation) {
		this.deateoperation = deateoperation;
	}

	public double getMontant() {
		return montant;
	}

	public void setMontant(double montant) {
		this.montant = montant;
	}

	

	public Operation(Date deatedecreation, double montant) {
		super();
		this.deateoperation = deatedecreation;
		this.montant = montant;
	}

	public double montant;
	
	private String libelle;
	
	public Operation(Date deateoperation, double montant, String libelle, Agence agence, Compt compte) {
		super();
		this.deateoperation = deateoperation;
		this.montant = montant;
		this.libelle = libelle;
		this.agence = agence;
		
	}

	@ManyToOne(fetch=FetchType.EAGER)
	protected Agence agence;
	
	
	
	public Agence getAgence() {
		return agence;
	}

	public void setAgence(Agence agence) {
		this.agence = agence;
	}

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="Code_compte")
	private Compt compte;
	
	

	

	public Compt getCompte() {
		return compte;
	}

	public void setCompte(Compt compte) {
		this.compte = compte;
	}

	public Operation(Date deateoperation, double montant, Agence agence, Compt compte) {
		super();
		this.deateoperation = deateoperation;
		this.montant = montant;
		this.agence = agence;
		
	}

	public Operation() {
		super();
	}

	public Operation(Date dateoperation, double montant2, Compt compte2) {
		// TODO Auto-generated constructor stub
		this.deateoperation=dateoperation;
		this.montant=montant2;
		
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	

}
