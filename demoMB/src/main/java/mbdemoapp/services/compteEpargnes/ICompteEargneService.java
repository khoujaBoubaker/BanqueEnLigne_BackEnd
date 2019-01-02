package mbdemoapp.services.compteEpargnes;

import org.springframework.data.domain.Page;

import mbdemoapp.domain.CompteCourant;
import mbdemoapp.domain.CompteEpargne;

public interface ICompteEargneService {
	
	public Page<CompteEpargne> chercherComptesEpargnesparIdClient(int idClient,int page,int size);
	
	// CRUD OPERATIONS
	public void addCompteEpargne(CompteEpargne ce);
	public Boolean deleteCompteEpargne(int idcompteepargne);
	public void updateCompteEpargne(CompteEpargne compteepargne);
	public CompteEpargne chercherCompteepargneById(int id);
	public CompteEpargne ajouterCompteEpargneToClient(int idclient,CompteEpargne ce);

}
