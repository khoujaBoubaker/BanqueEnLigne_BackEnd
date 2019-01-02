package mbdemoapp.comptesCourant.Services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import mbdemoapp.domain.Compt;
import mbdemoapp.domain.CompteCourant;


public interface CompteCourantsService {
	
	public Page<CompteCourant> chercherCompteCourantsParIdClient(int id,int page,int size);
	
	public Page<CompteCourant> chercherComptesCourantsTriesParDecouvert(int id,int page,int size);
	
	public Page<CompteCourant>chercherComptesCourantsTriesParSoldeDec(int id,int page,int size);
	
	public void addCompteCourant(CompteCourant cc);
	public boolean deleteCompteCourant(int id);
	public void updateCompteCourant(CompteCourant cc);
	public List<CompteCourant> listComptesCourants(int idclient);
	public CompteCourant chercherCompteCourantById(int id);
	
	public CompteCourant chercherCompteParNumeroDeCompte(String numeroDeCompte);
	
	public int size();
	

}
