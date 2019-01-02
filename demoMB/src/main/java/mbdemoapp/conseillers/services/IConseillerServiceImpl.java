package mbdemoapp.conseillers.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import mbdemoapp.agences.service.IAgenceService;
import mbdemoapp.domain.Agence;
import mbdemoapp.domain.Client;
import mbdemoapp.domain.Conseiller;
import mbdemoapp.repo.ConseillerRepo;

@Service
public class IConseillerServiceImpl implements IConseillerService {
	
	@Autowired
	private ConseillerRepo conseillerrepo;
	
	@Autowired
	private IAgenceService agenceService;

	@Override
	public Conseiller addConseiller(Conseiller conseiller) {
		// TODO Auto-generated method stub
		return conseillerrepo.save(conseiller);
	}

	@Override
	public void supprimerConseiller(int id) {
		// TODO Auto-generated method stub
		conseillerrepo.delete(findConseillerById(id));
	}

	@Override
	public void editConseiller(Conseiller conseiller) {
		// TODO Auto-generated method stub
		conseillerrepo.save(conseiller);
		
	}

	@Override
	public Conseiller findConseillerById(int id) {
		// TODO Auto-generated method stub
		return conseillerrepo.findOne(id);
	}

	

	public void setConseillerrepo(ConseillerRepo conseillerrepo) {
		this.conseillerrepo = conseillerrepo;
	}

	
	

	@Override
	public Page<Conseiller> chercherConseillersTriesParDateEmbache(String mc, int page, int size) {
		// TODO Auto-generated method stub
		return conseillerrepo._chercherConseillerParMotCleTriees(mc, new PageRequest(page, size));
	}

	@Override
	public Page<Conseiller> chercherConseillersTriesParNom(String mc, int page, int size) {
		// TODO Auto-generated method stub
		return conseillerrepo._chercherConseillerParMotCleTrieesParorderLexicographique(mc, new PageRequest(page, size));
	}

	@Override
	public void saveConseillerDansUneAgence(int idAgence, Conseiller conseiller) {
		// TODO Auto-generated method stub
		Agence agence=this.agenceService.findById(idAgence);
		conseiller.setAgence(agence);
		this.conseillerrepo.save(conseiller);
		
		
	}

	@Override
	public Page<Conseiller> chercherConseillersParAgence(int idagence, int page, int size) {
		// TODO Auto-generated method stub
		return this.conseillerrepo._chercherConseillerParAgence(idagence, new PageRequest(page, size));
	}

	@Override
	public List<Conseiller> TrouverListeConseillerParAgence(int idagence) {
		// TODO Auto-generated method stub
		return this.conseillerrepo._chercherListeConseilleParAgence(idagence);
	}

}
