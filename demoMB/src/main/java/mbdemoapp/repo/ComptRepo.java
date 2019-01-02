package mbdemoapp.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mbdemoapp.domain.Compt;
import mbdemoapp.domain.CompteCourant;

@Repository
public interface ComptRepo extends CrudRepository<Compt,Integer> {
	
	
	


}
