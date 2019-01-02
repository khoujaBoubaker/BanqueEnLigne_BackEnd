package mbdemoapp.services.compteEpargnes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import mbdemoapp.clients.services.IClientService;
import mbdemoapp.domain.Client;
import mbdemoapp.domain.CompteCourant;
import mbdemoapp.domain.CompteEpargne;
import mbdemoapp.repo.CompteEpargneRepo;

@Service
public class ICompteEpargneImpl implements ICompteEargneService{
	
	@Autowired
	private CompteEpargneRepo epargnerepo;
	
	
	@Autowired
	private IClientService clientService;

	@Override
	public Page<CompteEpargne> chercherComptesEpargnesparIdClient(int idClient, int page, int size) {
		// TODO Auto-generated method stub
		return epargnerepo.chercherComptesEpargne(idClient, new PageRequest(page, size));
	}

	@Override
	public void addCompteEpargne(CompteEpargne ce) {
		// TODO Auto-generated method stub
		epargnerepo.save(ce);
		
	}

	@Override
	public Boolean deleteCompteEpargne(int idcompteepargne) {
		// TODO Auto-generated method stub
		epargnerepo.delete(idcompteepargne);
		System.out.println("suppression effectuée");
		return true;
	}

	@Override
	public void updateCompteEpargne(CompteEpargne compteepargne) {
		// TODO Auto-generated method stub
		epargnerepo.save(compteepargne);
		
	}

	@Override
	public CompteEpargne chercherCompteepargneById(int id) {
		// TODO Auto-generated method stub
		return epargnerepo.findOne(id);
	}

	@Override
	public CompteEpargne ajouterCompteEpargneToClient(int idclient, CompteEpargne ce) {
		// TODO Auto-generated method stub
		Client client=this.clientService.findClientById(idclient);
		ce.setClient(client);
		epargnerepo.save(ce);
		return ce;
		
	}

}
