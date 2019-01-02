package mbdemoapp.domain;

import java.io.Serializable;
import java.util.Date;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Mensualite implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idmensulaite;
	
	public int getIdmensulaite() {
		return idmensulaite;
	}

	public void setIdmensulaite(int idmensulaite) {
		this.idmensulaite = idmensulaite;
	}

	public Credit getCredit() {
		return credit;
	}

	public void setCredit(Credit credit) {
		this.credit = credit;
	}

	@Override
	public String toString() {
		return "Mensualite [idmensulaite=" + idmensulaite + ", mensualite=" + mensualite + ", dateDePaiementPrevue="
				+ dateDePaiementPrevue + ", dateDePaiementEffective=" + dateDePaiementEffective + ", etat=" + etat
				+ ", credit=" + credit + "]";
	}

	private double mensualite;
	
	@Temporal(TemporalType.DATE)
	private Date dateDePaiementPrevue;
	
	@Temporal(TemporalType.DATE)
	private Date dateDePaiementEffective;
	
	private String etat;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="code_credit")
	private Credit credit;

	public double getMensualite() {
		return mensualite;
	}

	public void setMensualite(double mensualite) {
		this.mensualite = mensualite;
	}

	public Date getDateDePaiementPrevue() {
		return dateDePaiementPrevue;
	}

	public void setDateDePaiementPrevue(Date dateDePaiementPrevue) {
		this.dateDePaiementPrevue = dateDePaiementPrevue;
	}

	public Date getDateDePaiementEffective() {
		return dateDePaiementEffective;
	}

	public void setDateDePaiementEffective(Date dateDePaiementEffective) {
		this.dateDePaiementEffective = dateDePaiementEffective;
	}

	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

}
