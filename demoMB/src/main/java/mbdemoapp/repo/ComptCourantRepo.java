package mbdemoapp.repo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import mbdemoapp.domain.CompteCourant;

public interface ComptCourantRepo extends CrudRepository<CompteCourant,Integer> {
	
	@Query("select c from CompteCourant c where c.client.idclient=:x order by c.datedecreation desc")
	public Page<CompteCourant> chercherComptes(@Param("x")int idclient,Pageable pageable);
	
	@Query("select c from CompteCourant c where c.client.idclient=:x order by c.decouvert desc")
	public Page<CompteCourant> chercherComptesTrieesParDecouvert(@Param("x")int idclient,Pageable pageable);
	
	@Query("select c from CompteCourant c where c.client.idclient=:x order by c.solde desc")
	public Page<CompteCourant> chercherComptesTriesParSolde(@Param("x") int idclient,Pageable pageable);
	
	
	@Query("select c from CompteCourant c")
	public List<CompteCourant> chercherTousLesComptes();
	
	@Query("select c from CompteCourant c where c.numeroCompte=:x")
	public CompteCourant findCompteCourantBynumeroCompte(@Param("x") String numCompte);
	
	@Query("select c from CompteCourant c where c.client.idclient=:x order by c.datedecreation desc")
	public List<CompteCourant> chercherListeDesComptesCourantParClient(@Param("x") int idClient);

}
