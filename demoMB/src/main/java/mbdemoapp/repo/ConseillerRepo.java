package mbdemoapp.repo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import mbdemoapp.domain.Client;
import mbdemoapp.domain.Conseiller;


@Repository
public interface ConseillerRepo extends CrudRepository<Conseiller,Integer>{
	
	@Query("select c from Conseiller c where c.nom like :x")
	public Page<Conseiller> _chercherConseillerParMotCle(@Param("x")String mc,Pageable pageable);
	
	//@Query("select c from Client c where c.conseiller.id=:x")
	//public Page<Client> _chercherClientParConseiller(@Param("x")int idconseiller,Pageable pageable);
	
	@Query("select c from Conseiller c where c.nom like :x order by c.dateEmbauche desc")
	public Page<Conseiller> _chercherConseillerParMotCleTriees(@Param("x")String mc,Pageable pageable);
	
	@Query("select c from Conseiller c where c.nom like :x order by c.nom")
	public Page<Conseiller> _chercherConseillerParMotCleTrieesParorderLexicographique(@Param("x")String mc,Pageable pageable);
	
	@Query("select c from Conseiller c where c.nom like :x order by c.datedenaissance desc")
	public Page<Conseiller> _chercherConseillerParMotCleTrieesParDateDeNaissance(@Param("x")String mc,Pageable pageable);
	
	@Query("select c from Conseiller c where c.agence.idagence=:x")
	public Page<Conseiller> _chercherConseillerParAgence(@Param("x") int id,Pageable pageable);
	
	
	@Query("select c from Conseiller c where c.agence.idagence=:x")
	public List<Conseiller> _chercherListeConseilleParAgence(@Param("x") int idagence);

}
