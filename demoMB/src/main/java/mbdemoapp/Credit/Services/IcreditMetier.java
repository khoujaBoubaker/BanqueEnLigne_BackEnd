package mbdemoapp.Credit.Services;

import org.springframework.data.domain.Page;

import mbdemoapp.domain.Credit;

public interface IcreditMetier {
	
	public double SimulerCredit(Double c ,int duree,double taux);
	public double SimulerCreditAvecApportPersonnel(Double apport, Double c,int duree,double taux);
	public int SimulerCreditAvecApportPersonnelAvecTauxFixe(Double apport, Double c,int duree);
	public int SimulerCreditAvecTauxFixe(Double apport,Double c,int duree);
	
	public int coutTotalCreditAvecTauxFixe(Double apport,Double c,int duree);
	
	public Page<Credit> chercherCreditsParClient(int idclient,int page,int size);

}
