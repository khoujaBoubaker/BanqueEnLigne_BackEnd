package mbdemoapp.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import mbdemoapp.domain.CompteEpargne;

public interface CompteEpargneRepo extends CrudRepository<CompteEpargne,Integer> {
	
	@Query("select c from CompteEpargne c where c.client.idclient=:x")
	public Page<CompteEpargne> chercherComptesEpargne(@Param("x") int idclient,Pageable pageable);

}
