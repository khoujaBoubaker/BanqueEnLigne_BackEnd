package mbdemoapp.graphes.Domain;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class Graphe {
	
	private Date date;
	public Date getDate() {
		return date;
	}


	private double MontantVersements;
	private double MontantRetraits;
	private double MontantToutesOperations;
	
	public Graphe() {
		
	}
	
	




	
	@Override
	public String toString() {
		return "Graphe [date=" + date + ", MontantVersements=" + MontantVersements + ", MontantRetraits="
				+ MontantRetraits + ", MontantToutesOperations=" + MontantToutesOperations + "]";
	}







	public double getMontantVersements() {
		return MontantVersements;
	}
	public void setMontantVersements(double montantVersements) {
		MontantVersements = montantVersements;
	}
	public double getMontantRetraits() {
		return MontantRetraits;
	}
	public void setMontantRetraits(double montantRetraits) {
		MontantRetraits = montantRetraits;
	}
	public double getMontantToutesOperations() {
		return MontantToutesOperations;
	}
	public void setMontantToutesOperations(double montantToutesOperations) {
		MontantToutesOperations = montantToutesOperations;
	}


	public void setDate(Date dt) {
		// TODO Auto-generated method stub
		this.date=dt;
		
	}


	

}
