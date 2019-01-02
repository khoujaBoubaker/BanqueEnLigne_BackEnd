package mbdemoapp.services.BanqueMetier;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javassist.expr.Instanceof;
import mbdemoapp.Credit.Services.IcreditMetier;
import mbdemoapp.compteCourantSoldes.ICompteCourantUtil;
import mbdemoapp.domain.Agence;
import mbdemoapp.domain.Client;
import mbdemoapp.domain.Compt;
import mbdemoapp.domain.CompteCourant;
import mbdemoapp.domain.CompteEpargne;
import mbdemoapp.domain.Credit;
import mbdemoapp.domain.Mensualite;
import mbdemoapp.domain.Operation;
import mbdemoapp.domain.Retraitt;
import mbdemoapp.domain.Versement;
import mbdemoapp.repo.AgenceRepository;
import mbdemoapp.repo.ClientRepo;
import mbdemoapp.repo.ComptCourantRepo;
import mbdemoapp.repo.ComptRepo;
import mbdemoapp.repo.CreditRepo;
import mbdemoapp.repo.MensualiteRepo;
import mbdemoapp.repo.OperationRepo;


@Service
public class IBanqueMetierImpl implements IBanqueMetier {
	
	@Autowired
	private ComptCourantRepo comptCourantRepository;
	
	@Autowired
	private ClientRepo clientRepo;
	
	@Autowired
	private MensualiteRepo mensulaiteRepo;
	
	@Autowired
	private IBanqueMetier banqueMetier;
	
	@Autowired
	private CreditRepo creditRepo;
	
	@Autowired
	private ICompteCourantUtil courantUtil;
	
	@Autowired
	private OperationRepo operationrepo;
	
	@Autowired
	private AgenceRepository agenceRepository;
	
	@Autowired
	private IcreditMetier creditMetier;
	
	
	public CompteCourant consulterCompteCourant(int codeCompte) {
		// TODO Auto-generated method stub
		CompteCourant cc=this.comptCourantRepository.findOne(codeCompte);
		if(cc==null) throw new RuntimeException("compte courant introuvable");
		return cc;
	}

	
	// retirer de l'argent
	@Override
	public void retirer(int idCompte, double montant, String libelle) {
		// TODO Auto-generated method stub
		
		CompteCourant comptecourant=consulterCompteCourant(idCompte);
		if(comptecourant.getSolde()+comptecourant.getDecouvert()<montant)
			throw new RuntimeException("solde insuffisant");
		Retraitt retrait=new Retraitt(new Date(), montant,comptecourant,libelle);
		retrait.setCompte(comptecourant);
		this.operationrepo.save(retrait);
		comptecourant.setSolde(comptecourant.getSolde()-montant);
		comptCourantRepository.save(comptecourant);
		
		
		
	}

	@Override
	public Page<Operation> listerOperations(int codeCompte, int page, int size) {
		// TODO Auto-generated method stub
		return this.operationrepo.listeOperationParCompte(codeCompte,new PageRequest(page, size));
	}

	@Override
	public Boolean deleteOperation(int idOperation) {
		// TODO Auto-generated method stub
	  this.operationrepo.delete(idOperation);
	  return true;
	}

	@Override
	public Operation findOperationById(int id) {
		// TODO Auto-generated method stub
		return this.findOperationById(id);
	}

	
	// verser de l'argent 
	@Override
	public void verser(int idCompte, double montant, String libelle) {
		// TODO Auto-generated method stub
		CompteCourant compteCourant=consulterCompteCourant(idCompte);
		Versement versement=new Versement(new Date(),montant,compteCourant,libelle);
		versement.setCompte(compteCourant);
		operationrepo.save(versement);
		compteCourant.setSolde(compteCourant.getSolde()+montant);
		this.comptCourantRepository.save(compteCourant);
		
	}
	
	// Vesrsement dans une agnece precise récupérée via son ID
	
	public void verserDansUneAgence(int idCompte,double montant,String libelle,int idAgence) {
		CompteCourant comptecourant=consulterCompteCourant(idCompte);
		Agence agence=this.agenceRepository.findOne(idAgence);
		Versement versement=new Versement(new Date(), montant, comptecourant);
		versement.setCompte(comptecourant);
		versement.setLibelle(libelle);
		versement.setAgence(agence);
		versement.setSoldeDuJour(comptecourant.getSolde()+montant);
		operationrepo.save(versement);
	    comptecourant.setSolde(comptecourant.getSolde()+montant);
	    this.comptCourantRepository.save(comptecourant);
		
	}
	
	
	// Retirer d'un agence
	
	public void RetirerDuneAgence(int idCompte,double montant,String libelle,int idAgence) {
		CompteCourant comptecourant=consulterCompteCourant(idCompte);
		Agence agence=this.agenceRepository.findOne(idAgence);
		if(comptecourant.getSolde()+comptecourant.getDecouvert()<montant)
			throw new RuntimeException("solde insuffisant");
		
		Retraitt retraitt=new Retraitt(new Date(),montant,comptecourant);
		retraitt.setLibelle(libelle);
		retraitt.setAgence(agence);
		retraitt.setCompte(comptecourant);
		retraitt.setSoldeDuJour(comptecourant.getSolde()-montant);
		operationrepo.save(retraitt);
		comptecourant.setSolde(comptecourant.getSolde()-montant);
		this.comptCourantRepository.save(comptecourant);
		
	}
	
	public void RetirerDuneAgencePourPayerMensualite(int idCompte,double montant,String libelle,int idAgence,int idcredit) {
		
		Credit credit=this.findCreditById(idcredit);
		Agence agence=this.agenceRepository.findOne(idAgence);
		CompteCourant compteCourant=consulterCompteCourant(idCompte);
		
		if(compteCourant.getSolde()+compteCourant.getDecouvert()<montant)
			throw new RuntimeException("solde insuffisant pour régler mensualité");
		
		Retraitt retrait=new Retraitt(new Date(),montant,compteCourant);
		retrait.setLibelle(libelle);
		retrait.setAgence(agence);
		retrait.setCompte(compteCourant);
		retrait.setSoldeDuJour(compteCourant.getSolde()-montant);
		operationrepo.save(retrait);
		compteCourant.setSolde(compteCourant.getSolde()-montant);
		this.comptCourantRepository.save(compteCourant);
		credit.setMontantResteApayer(credit.getMontantResteApayer()-montant);
		this.banqueMetier.ajouterCredit(credit);
		
	}


	@Override
	public void virement(int codeCompteDebiteur, int codeCompteCrediteur, double montant, String libelle,
			int idAgence) {
		// TODO Auto-generated method stub
		this.RetirerDuneAgence(codeCompteDebiteur, montant, libelle, idAgence);
		this.verserDansUneAgence(codeCompteCrediteur, montant, libelle, idAgence);
		System.out.println("virement effectué avec succès ..");
	}


	@Override
	public Page<Operation> listerOperationsParDate(int codecompte, Date dateOp, int page, int size) {
		// TODO Auto-generated method stub
		return this.operationrepo.listeOperationsParCompteEtParDate(codecompte, dateOp,new PageRequest(page, size));
	}


	@Override
	public Page<Operation> listerRetraits(int codeCompte, int page, int size) {
		// TODO Auto-generated method stub
		return this.operationrepo.listerRetraitsParCompte(codeCompte,new PageRequest(page, size));
	}


	@Override
	public Page<Operation> listerVersements(int codeCompte, int page, int size) {
		// TODO Auto-generated method stub
		return this.operationrepo.listeVersementsParCompte(codeCompte, new PageRequest(page, size));
	}


	@Override
	public double soldeTotalComptesCourants(int idClient) {
		// TODO Auto-generated method stub
		double solde=0;
		for(CompteCourant cc:this.courantUtil.listerCompteCourantParIdClient(idClient))
			solde=solde+cc.getSolde();
		return solde;
	}


	@Override
	public double soldeTotalComptesEpargnes(int idClient) {
		// TODO Auto-generated method stub
		double soldeEpargne=0;
		for(CompteEpargne ce:this.courantUtil.listerComptesEpargnesParIdClient(idClient))
			soldeEpargne=soldeEpargne+ce.getSolde();
		return soldeEpargne;
	}




	@Override
	public List<Operation> findAllOperations() {
		// TODO Auto-generated method stub
		return this.operationrepo.findall();
	}


	@Override
	public double BusinessSolde() {
		// TODO Auto-generated method stub
		double solde= 0;
		SimpleDateFormat format=new SimpleDateFormat("MM/dd/yyyy");
		Date dcurseur=null;
		
		for(Operation o:this.findAllOperations()) {
			
			    Date dateDuJour=new Date();
				dcurseur=o.deateoperation;
				long diff=dateDuJour.getTime()-dcurseur.getTime();
				long diffHeures=diff/(24*60*60*1000);
				System.out.println(diffHeures);
				if(diffHeures <=7) {
					solde+=o.montant;
				}
				
				}
		return solde;
	}


	@Override
	public double BusinessChifferAffaireSelonCriteres(int i) {
		// TODO Auto-generated method stub
		double solde=0;
		SimpleDateFormat format=new SimpleDateFormat("MM/dd/yyyy");
		Date dcurseur=null;
		
		for(Operation o:this.findAllOperations()) {
			Date dateDuJour=new Date();
			dcurseur=o.deateoperation;
			long diff=dateDuJour.getTime()-dcurseur.getTime();
			long diffHeures=diff/(24*60*60*1000);
			System.out.println(diffHeures);
			if(diffHeures <= i) {
				solde+=o.montant;
			}
		}
		return solde;
	}


	@Override
	public Boolean AjouterCreditAunClient(int idclient, Credit credit) {
		// TODO Auto-generated method stub
		Client client=this.ConsulterClient(idclient);
		credit.setDateEmprunt(new Date());
		credit.setClient(client);
		this.creditRepo.save(credit);
		// Traiter les mensualités
		
		
		
		for(int i=0;i<credit.getDuree();i++)
		{
			Mensualite mensualite=new Mensualite();
			mensualite.setCredit(credit);
			mensualite.setMensualite(this.creditMetier.SimulerCreditAvecApportPersonnelAvecTauxFixe(credit.getApport(),credit.getMontantemprunte(),credit.getDuree()));
			
			
			Date date=credit.getDateEmprunt();
			DateFormat dateformat=new SimpleDateFormat("yyyy/MM/dddd");
			Calendar calendar=Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(Calendar.MONTH,i+1);
			Date currentDateplusi=calendar.getTime();
			mensualite.setDateDePaiementPrevue(currentDateplusi);
			mensualite.setEtat("NP");
			this.mensulaiteRepo.save(mensualite);
			System.out.println("ajout numero :"+i);
		}
	
		return true;
		
	}


	@Override
	public Client ConsulterClient(int idClient) {
		// TODO Auto-generated method stub
		return this.clientRepo.findOne(idClient);
	}


	public CreditRepo getCreditRepo() {
		return creditRepo;
	}


	public void setCreditRepo(CreditRepo creditRepo) {
		this.creditRepo = creditRepo;
	}


	public MensualiteRepo getMensulaiteRepo() {
		return mensulaiteRepo;
	}


	public void setMensulaiteRepo(MensualiteRepo mensulaiteRepo) {
		this.mensulaiteRepo = mensulaiteRepo;
	}


	@Override
	public Boolean SupprimerCredit(int id) {
		// TODO Auto-generated method stub
		this.creditRepo.delete(this.findCreditById(id));
		return true;
	}


	@Override
	public Credit findCreditById(int idcredit) {
		// TODO Auto-generated method stub
		return this.creditRepo.findOne(idcredit);
	}


	@Override
	public Page<Mensualite> listeDesMensualites(int idcredit, int page, int size) {
		// TODO Auto-generated method stub
		return this.mensulaiteRepo.chercherMensualitesParCredit(idcredit, new PageRequest(page, size));
	}


	@Override
	public Boolean ajouterCredit(Credit credit) {
		// TODO Auto-generated method stub
		this.creditRepo.save(credit);
		return true;
	}




	
	

}
