package mbdemoapp.repo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import mbdemoapp.domain.Agence;
import mbdemoapp.domain.Client;

@Repository
public interface ClientRepo extends CrudRepository<Client,Integer>  {
	
	@Query("select c from Client c where c.nom like :x")
	public Page<Client> _chercherClientParMotCle(@Param("x")String mc,Pageable pageable);
	
	public Client findByemail(String email);
	
	@Query("select c from Client c where c.nom like :x order by salaire desc")
	public Page<Client> _chercherClientParMotCleEtParSalaire(@Param("x")String mc,Pageable pageable);
	
	@Query("select c from Client c where c.nom like :x order by salaire")
	public Page<Client> _chercherClientsParMotCleEtParSalaireParOrdre(@Param("x")String mc,Pageable pageable);
	
	@Query("select c from Client c where c.nom like :x order by datedenaissance desc")
	public Page<Client> _chercherClientParMotCleEtPardtn(@Param("x")String mc,Pageable pageable);
	
	
	@Query("select c from Client c where c.nom like :x order by dateDeCreation desc")
	public Page<Client> _chercherClientParMotCleEtPardtc(@Param("x")String mc,Pageable pageable);
	
	// chercher clients par agence donnée
	@Query("select c from Client c where c.agence.idagence=:x")
	public Page<Client> _chercherClientsParAgence(@Param("x") int id,Pageable pageable);
	
	@Query("select c from Client c where c.idclient!=:x")
	public Page<Client> _chercherClientsPourEffectuerVirement(@Param("x")int id,Pageable pageable);
	
	@Query("select c from Client c where c.idclient !=:x and c.nom like :s")
	public List<Client> _chercherListeClientPourEffectuerVirement(@Param("x") int id,@Param("s") String s);
	
	
	//  fonction retenue
	@Query("select c from Client c where c.idclient !=:x and c.nom like :s")
	public Page<Client> _ChercherClientsToVirement(@Param("s") String motCle,@Param("x") int id,Pageable pageable);
	
	// trier les données par email.
	@Query("select c from Client c where c.nom like :x order by email desc")
	public Page<Client> _chercherClientsParEmailDecroissant(@Param("x") String mc,Pageable pageable);
	
	@Query("select c from Client c where c.nom like :x order by email")
	public Page<Client> _chercherClientsParEmailCroissant(@Param("x") String mc,Pageable pageable);
	
	@Query("select c from Client c")
	public List<Client> ListeDeTousLesClients();
	
	
	
	
	
	
	
	

}
