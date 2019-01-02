package mbdemoapp.clients.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import mbdemoapp.Fournisseurs.Comptes.CleNumComptes.INumCompte;
import mbdemoapp.agences.service.IAgenceService;
import mbdemoapp.comptes.services.ICompteService;
import mbdemoapp.comptesCourant.Services.CompteCourantsService;
import mbdemoapp.domain.Agence;
import mbdemoapp.domain.Client;
import mbdemoapp.domain.CompteCourant;
import mbdemoapp.repo.ClientRepo;

@Service
public class IClientServiceImpl implements IClientService{
	
	@Autowired
	private INumCompte _InumCompte; 
	
	@Autowired
	private ClientRepo clientrepo;
	
	@Autowired
	private CompteCourantsService compteCourantService;
	
	@Autowired
	private IAgenceService agenceService;
	
	

	@Override
	public Client addClient(Client client) {
		clientrepo.save(client);
		return client;
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Client> getallclients() {
		// TODO Auto-generated method stub
		return (List<Client>) clientrepo.findAll();
	}

	@Override
	public Client findClientByName(String nom) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Client findClientByEmail(String email) {
		// TODO Auto-generated method stub
		return clientrepo.findByemail(email);
	}

	@Override
	public void deleteClient(int id) {
		// TODO Auto-generated method stub
		clientrepo.delete(this.findClientById(id));
		
	}

	public ClientRepo getClientrepo() {
		return clientrepo;
	}

	public void setClientrepo(ClientRepo clientrepo) {
		this.clientrepo = clientrepo;
	}

	@Override
	public Client findClientById(int id) {
		// TODO Auto-generated method stub
		return clientrepo.findOne(id);
	}



	@Override
	public Page<Client> _chercherClientParMotCleEtParsalary(String mc, int page, int size) {
		// TODO Auto-generated method stub
		return clientrepo._chercherClientParMotCleEtParSalaire(mc, new PageRequest(page, size));
	}

	@Override
	public Page<Client> _chercherClientParMotCleEtPardtn(String mc, int page, int size) {
		// TODO Auto-generated method stub
		return clientrepo._chercherClientParMotCleEtPardtn("%"+mc+"%",new PageRequest(page, size));
	}

	@Override
	public Page<Client> _chercherClientParMotCleEtPardtc(String mc, int page, int size) {
		// TODO Auto-generated method stub
		return clientrepo._chercherClientParMotCleEtPardtc(mc,new PageRequest(page, size));
	}

	@Override
	public void ajouterCompteCourantAunClient(int idclient, CompteCourant cc) {
		
		int nombreComptesTotal=this.compteCourantService.size();
		
		
		
		// TODO Auto-generated method stub
		Client client=this.clientrepo.findOne(idclient);
		
		String nomClient=client.getNom();
		String prenomClient=client.getPrenom();
		
		String numCompte=this._InumCompte.NumCompte(nomClient, prenomClient, nombreComptesTotal);
		
		cc.setClient(client);
		cc.setNumeroCompte(numCompte);
		this.compteCourantService.addCompteCourant(cc);
		System.out.println("compte ajouté");
		
		
	}

	@Override
	public void saveClientInAgence(int idagence, Client client) {
		// TODO Auto-generated method stub
		Agence agence=this.agenceService.findById(idagence);

		
	}

	@Override
	public Page<Client> _chercherClientsParAgence(int idagence, int page, int size) {
		// TODO Auto-generated method stub
		return clientrepo._chercherClientsParAgence(idagence, new PageRequest(page, size));
	}

	@Override
	public Page<Client> _chercherClientsPourVirement(int idClient, int page, int size) {
		// TODO Auto-generated method stub
		return clientrepo._chercherClientsPourEffectuerVirement(idClient,new PageRequest(page, size));
	}

	

	@Override
	public Page<Client> _chercherClientsPourVirement(int idclient, String motCle, int page, int size) {
		// TODO Auto-generated method stub
		return 
	    this.clientrepo._ChercherClientsToVirement("%"+motCle+"%", idclient, new PageRequest(page, size));
	}

	@Override
	public Page<Client> _chercherClientParMotCleEtParSalaryParOrdreCroissant(String mc, int page, int size) {
		// TODO Auto-generated method stub
		return this.clientrepo._chercherClientsParMotCleEtParSalaireParOrdre("%"+mc+"%",new PageRequest(page, size));
	}

	@Override
	public Page<Client> _chercherClientsParMotCleEtParEmailParOrdreCroissant(String mc, int page, int size) {
		// TODO Auto-generated method stub
		return this.clientrepo._chercherClientsParEmailCroissant("%"+mc+"%",new PageRequest(page, size));
	}

	@Override
	public Page<Client> chercherCleintsParMotCleEtParEmailParOrdreDecroissant(String mc, int page, int size) {
		// TODO Auto-generated method stub
		return this.clientrepo._chercherClientsParEmailDecroissant("%"+mc+"%", new PageRequest(page, size));
	}

	@Override
	public int sizeclient() {
		// TODO Auto-generated method stub
		
		List<Client>cls=this.clientrepo.ListeDeTousLesClients();
		return cls.size();
	}

	


}
