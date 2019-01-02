package mbdemoapp.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import mbdemoapp.domain.Credit;

@Repository
public interface CreditRepo extends CrudRepository<Credit,Integer> {
	
	@Query("select c from Credit c where c.client.idclient=:x")
	public Page<Credit> chercherCreditsParClient(@Param("x") int code_client,Pageable pageable);
	
	

}
