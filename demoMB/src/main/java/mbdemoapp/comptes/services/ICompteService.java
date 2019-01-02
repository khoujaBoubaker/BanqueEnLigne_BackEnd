package mbdemoapp.comptes.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import mbdemoapp.domain.Compt;


@Service
public interface ICompteService {
	
	public Compt addCompte(Compt compte);
	public Compt findCompteById(int id);
	public void deleteCompte(int idCompte);
	
	public Page<Compt> _chercherComptesParIdClientTriesParDateDeCreation(int idcompte,int page,int size);
	public Page<Compt> _chercherComptesParIdClientTriesParDecouvert(int idcompte,int page,int size);

}
