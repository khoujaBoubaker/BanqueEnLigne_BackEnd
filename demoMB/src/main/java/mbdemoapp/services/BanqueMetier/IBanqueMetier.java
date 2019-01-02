package mbdemoapp.services.BanqueMetier;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;

import mbdemoapp.domain.Client;
import mbdemoapp.domain.CompteCourant;
import mbdemoapp.domain.Credit;
import mbdemoapp.domain.Mensualite;
import mbdemoapp.domain.Operation;
import mbdemoapp.domain.Retraitt;

public interface IBanqueMetier {
	
	
	public CompteCourant consulterCompteCourant(int codeCompte);	
	// pouvoir retirer d'une agence.
	public void retirer (int idCompte,double montant,String libelle);
	
	public void verser(int idCompte,double montant,String libelle);
	
	public Page<Operation> listerOperations(int codeCompte,int page,int size);
	
	public Page<Operation> listerRetraits(int codeCompte,int page,int size);
	
	// liste de tous les versements par compte .
	public Page<Operation> listerVersements(int codeCompte,int page,int size);
	
	public Page<Operation> listerOperationsParDate(int codecompte,Date dateOp,int page,int size);
	
	// DELETE OPERATION
	public Boolean deleteOperation(int idOperation);
	public Operation findOperationById(int id);
	
	
	public void verserDansUneAgence(int idCompte, double montant,String libelle,int idagence);
	public void RetirerDuneAgence(int idCompte,double montant,String libelle,int idAgence);
	// retirer pour payer mensualité
	public void RetirerDuneAgencePourPayerMensualite(int idCompte,double montant,String libelle,int idAgence,int idcredit);
	public void virement(int codeCompteDebiteur,int codeCompteCrediteur,double montant,String libelle,int idAgence);
	
	public double soldeTotalComptesCourants(int idClient);
	
	public double soldeTotalComptesEpargnes(int idClient);
	
	public List<Operation> findAllOperations();
	
	public double BusinessSolde();
	
	public double BusinessChifferAffaireSelonCriteres(int i);
	
	public Boolean AjouterCreditAunClient(int idclient,Credit credit);
	public Boolean SupprimerCredit(int id);
	public Credit findCreditById(int idcredit);
	public Client ConsulterClient(int idClient);
	
	public Boolean ajouterCredit(Credit credit);
	
	
	// service de mensulaites
	public Page<Mensualite> listeDesMensualites(int idcredit,int page,int size);
	

	
	
	
	
	

}
