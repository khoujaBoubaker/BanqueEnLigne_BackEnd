package mbdemoapp.repo;


import org.assertj.core.data.Percentage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import mbdemoapp.domain.Administrateur;

@Repository
public interface AdminRepo extends CrudRepository<Administrateur,Integer>{
	
	
	
	@Query("select a from Administrateur a where a.nom like :x")
	public Page<Administrateur> _chercher(@Param("x")String mc,Pageable pageable);
	
	
	public Administrateur findByUsername(String username);
	
	
	@Query("select a from Administrateur a where a.username=:login and a.password=:password")
	public Administrateur findAdminByUsernameAndPassword(@Param("login") String login,@Param("password") String password);

}
