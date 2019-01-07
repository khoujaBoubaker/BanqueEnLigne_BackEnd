package mbdemoapp.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import mbdemoapp.domain.Administrateur;
import mbdemoapp.domain.Agence;


@Repository
public interface AgenceRepository extends CrudRepository<Agence,Integer>{
	
	public Agence findByAddresse(String addresse);
	
	@Query("select a from Agence a where a.addresse like :x")
	public Page<Agence> _chercherAgenceParMotCle(@Param("x")String mc,Pageable pageable);
	
	@Query("select a from Agence a where a.addresse like :x order by commune")
	public Page<Agence> _chercherAgenceParMotCleTriesParCommune(@Param("x")String mc,Pageable pageable);

}
