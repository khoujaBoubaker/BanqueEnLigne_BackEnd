package mbdemoapp.repo;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import mbdemoapp.domain.RendezVous;


@Repository
public interface RendezVousRepository extends JpaRepository<RendezVous,Integer> {
	
	@Query("select r from RendezVous r")
	public Page<RendezVous> listeRendezVous(Pageable pageable);
	
	@Query("select r from RendezVous r where r.client.idclient=:x AND r.libelle like :y order by r.dateRendezVous desc")
	public Page<RendezVous> listeRendezVousParClient(@Param("x") int idClient,@Param("y") String motcle,Pageable pageable);

    
	

}
