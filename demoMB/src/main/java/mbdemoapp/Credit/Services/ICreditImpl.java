package mbdemoapp.Credit.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import mbdemoapp.domain.Credit;
import mbdemoapp.repo.CreditRepo;

@Service
public class ICreditImpl implements IcreditMetier {
	
	@Autowired
	private CreditRepo creditRepo;

	@Override
	public double SimulerCredit(Double c, int duree, double taux) {
		// TODO Auto-generated method stub
		double t=taux/100;
		double t1=c*t/12;
	    double t2=1-Math.pow((1+t/12),-1*duree);
	    return t1/t2;
		}

	@Override
	public double SimulerCreditAvecApportPersonnel(Double apport, Double c, int duree, double taux) {
		// TODO Auto-generated method stub
		double t=taux/100;
		double t1=(c-apport)*t/12;
		double t2=1-Math.pow((1+t/12),-1*duree);
		return t1/t2;
		
	}

	@Override
	public int SimulerCreditAvecTauxFixe(Double apport, Double c, int duree) {
		// TODO Auto-generated method stub
		double taux=4.5;
		double t=taux/100;
		double t1=(c-apport)*t/12;
		double t2=1-Math.pow((1+t/12),-1*duree);
		return (int) (t1/t2);
		
		
	}

	@Override
	public int SimulerCreditAvecApportPersonnelAvecTauxFixe(Double apport, Double c, int duree) {
		// TODO Auto-generated method stub
		double taux=4.5;
		double t=taux/100;
		double t1=(c-apport)*t/12;
		double t2=1-Math.pow((1+t/12),-1*duree);
		return (int) (t1/t2);
	}

	@Override
	public int coutTotalCreditAvecTauxFixe(Double apport, Double c, int duree) {
		// TODO Auto-generated method stub
		double taux=4.5;
		double t=taux/100;
		double t1=(c-apport)*t/12;
		double t2=1-Math.pow((1+t/12),-1*duree);
		return (int) (duree*t1/t2);
	}

	@Override
	public Page<Credit> chercherCreditsParClient(int idclient, int page, int size) {
		// TODO Auto-generated method stub
		return this.creditRepo.chercherCreditsParClient(idclient,new PageRequest(page, size));
	}



}
