package mbdemoapp.domain;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("V")
public class Versement extends Operation {
	
	private String op="versement";
	
	public String getOp() {
		return op;
	}

	public void setOp(String op) {
		this.op = op;
	}

	public Versement() {
		super();
		
	}
	
	public Versement(Date dateoperation,double montant,Compt compte) {
		super(dateoperation,montant,compte);
		}
	
	public Versement(Date dateoperation,double montant,Compt compte,Agence agence) {
		super(dateoperation,montant,compte);
	         this.setAgence(agence);
	        
		}
	

	public Versement(Date dateoperation,double montant,Compt compte,Agence agence,String libelle) {
		super(dateoperation,montant,compte);
	         this.setAgence(agence);
	         setLibelle(libelle);
	        
		}
	
	public Versement(Date dateoperation,double montant,Compt compte,String libelle) {
		super(dateoperation,montant,compte);
	        
	         setLibelle(libelle);
	        
		}

}
