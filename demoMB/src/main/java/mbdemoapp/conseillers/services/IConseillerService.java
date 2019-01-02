package mbdemoapp.conseillers.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import mbdemoapp.domain.Client;
import mbdemoapp.domain.Conseiller;

@Service
public interface IConseillerService {
	
	public Conseiller addConseiller(Conseiller conseiller);
	public void supprimerConseiller(int id);
	public void editConseiller(Conseiller conseiller);
	public Conseiller findConseillerById(int id);
	
	public void saveConseillerDansUneAgence(int idAgence,Conseiller conseiller);
	
	//public Page<Client> chercherClientParConseillers(int idconseiller,int page,int size);
	
	// TRI CONSEILLERS PAR DATE EMBAUCHE
	public Page<Conseiller> chercherConseillersTriesParDateEmbache(String mc,int page,int size);
	
	//TRI CONSEILLERS PAR NOM
	public Page<Conseiller> chercherConseillersTriesParNom(String mc,int page,int size);
	
	public Page<Conseiller> chercherConseillersParAgence(int idagence,int page,int size);
	
    public List<Conseiller> TrouverListeConseillerParAgence(int idagence);
}
