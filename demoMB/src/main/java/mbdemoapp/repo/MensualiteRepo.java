package mbdemoapp.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import mbdemoapp.domain.Mensualite;

@Repository
public interface MensualiteRepo extends CrudRepository<Mensualite,Integer>  {
	
	@Query("select m from Mensualite m where m.credit.id=:x")
	public Page<Mensualite> chercherMensualitesParCredit(@Param("x") int code,Pageable pageable);

}
