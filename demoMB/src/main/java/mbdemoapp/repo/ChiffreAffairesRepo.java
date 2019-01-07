package mbdemoapp.repo;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import mbdemoapp.domain.Operation;

@Repository
public interface ChiffreAffairesRepo extends CrudRepository<Operation,Integer> {
	
	@Query("Select o from Operation o where o.deateoperation=:dt")
	public List<Operation> listeOperationsPourGraphique(@Param("dt") Date date);

}
