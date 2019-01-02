package mbdemoapp.comptesCourant.Services;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import mbdemoapp.Fournisseurs.Comptes.CleNumComptes.INumCompte;
import mbdemoapp.clients.services.IClientService;
import mbdemoapp.domain.Client;
import mbdemoapp.domain.Compt;
import mbdemoapp.domain.CompteCourant;
import mbdemoapp.repo.ComptCourantRepo;

@Service
public class CompteCourantServiceImpl implements CompteCourantsService{
	
	@Autowired
	private INumCompte numCompte;
	
	@Autowired
	private ComptCourantRepo comptcourantrepo;
	
	@Autowired
	private IClientService clientService;

	@Override
	public Page<CompteCourant> chercherCompteCourantsParIdClient(int id, int page, int size) {
		// TODO Auto-generated method stub
		return this.comptcourantrepo.chercherComptes(id,new PageRequest(page, size));
	}

	@Override
	public void addCompteCourant(CompteCourant cc) {
		// TODO Auto-generated method stub
		int nobreDeComptes=this.size();
	    //	String numCPT=this.numCompte.NumCompte(nom, prenom, id)
		
		
		this.comptcourantrepo.save(cc);
		System.out.println("compte courant ajouté avec succés");
		
	}



	@Override
	public void updateCompteCourant(CompteCourant cc) {
		// TODO Auto-generated method stub
		this.comptcourantrepo.save(cc);
		System.out.println("compte courant sauvegardé");
		
	}

	

	@Override
	public CompteCourant chercherCompteCourantById(int id) {
		// TODO Auto-generated method stub
		return this.comptcourantrepo.findOne(id);
	}

	@Override
	public boolean deleteCompteCourant(int id) {
		// TODO Auto-generated method stub
		this.comptcourantrepo.delete(id); 
		return true;
	}

	@Override
	public List<CompteCourant> listComptesCourants(int idclient) {
		// TODO Auto-generated method stub
		Client client=this.clientService.findClientById(idclient);
	    return null;
		
	}

	@Override
	public Page<CompteCourant> chercherComptesCourantsTriesParDecouvert(int id, int page, int size) {
		// TODO Auto-generated method stub
		return comptcourantrepo.chercherComptesTrieesParDecouvert(id, new PageRequest(page, size));
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return this.comptcourantrepo.chercherTousLesComptes().size();
	}

	@Override
	public CompteCourant chercherCompteParNumeroDeCompte(String numeroDeCompte) {
		// TODO Auto-generated method stub
		return 
				this.comptcourantrepo.findCompteCourantBynumeroCompte(numeroDeCompte);
	}

	@Override
	public Page<CompteCourant> chercherComptesCourantsTriesParSoldeDec(int id, int page, int size) {
		// TODO Auto-generated method stub
		return this.comptcourantrepo.chercherComptesTriesParSolde(id,new PageRequest(page, size));
	}

}
