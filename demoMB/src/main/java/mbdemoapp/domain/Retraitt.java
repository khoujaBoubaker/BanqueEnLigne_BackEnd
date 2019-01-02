package mbdemoapp.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("R")
public class Retraitt extends Operation implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String op="retrait";


	
	public Retraitt() {
		super();
	}
	
	public Retraitt(Date deatedecreation, double montant,Compt cpt) {
		super(deatedecreation, montant,cpt);
		
		
		
	}
	
	public Retraitt(Date deatedecreation, double montant,Compt cpt,String libelle) {
		super(deatedecreation, montant,cpt);
		setLibelle(libelle);
		
		
		
	}
	
	
	
	public Retraitt(Date deatedecreation, double montant, String op,String libelle) {
		super(deatedecreation, montant);
		this.op = op;
		setLibelle(libelle);
		
		
	}
	
	public Retraitt(Date deatedecreation, double montant, String op,String libelle,Agence agence) {
		super(deatedecreation, montant);
		this.op = op;
		setLibelle(libelle);
		setAgence(agence);
	}

	public Retraitt(Date date, double montant, Compt cp, Agence agence) {
		// 
		super(date,montant,cp);
        this.setAgence(agence);
        
      }

	public String getOp() {
		return op;
	}

	public void setOp(String op) {
		this.op = op;
	}

}
