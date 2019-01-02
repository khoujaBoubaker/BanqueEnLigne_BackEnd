package mbdemoapp.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "CC")
public class CompteCourant extends Compt implements Serializable {
	
	private double decouvert;
	
	
	
	// constructor par défaut
	public CompteCourant() {
		super();
		this.setDecouvert(decouvert);
	}

	public double getDecouvert() {
		return decouvert;
	}

	@Override
	public String toString() {
		
		return "CompteCourant [decouvert=" + decouvert + "]";
	}

	public void setDecouvert(double decouvert) {
		this.decouvert = decouvert;
	}

	

}
