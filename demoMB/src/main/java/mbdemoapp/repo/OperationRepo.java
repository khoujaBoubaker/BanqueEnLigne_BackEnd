package mbdemoapp.repo;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import mbdemoapp.domain.Operation;
import mbdemoapp.domain.Retraitt;

@Repository
public interface OperationRepo extends JpaRepository<Operation,Integer>{
	
	@Query("Select o from Operation o where o.compte.idcpt=:x order by o.deateoperation desc")
	public Page<Operation> listeOperationParCompte(@Param("x") int codeCompte,Pageable pageable);
	
	@Query("Select r from Retraitt r where r.compte.idcpt=:x order by r.deateoperation desc")
	public Page<Operation> listerRetraitsParCompte(@Param("x")int codeCompte,Pageable pageable);
	
	@Query("Select v from Versement v where v.compte.idcpt=:x")
	public Page<Operation> listeVersementsParCompte(@Param("x") int codeCompte,Pageable pageable);
	
	@Query("Select o from Operation o where o.compte.idcpt=:x and o.deateoperation=:dt order by o.deateoperation desc")
	public Page<Operation> listeOperationsParCompteEtParDate(@Param("x")int codeCompte,@Param("dt") Date date,Pageable pageable);
	
	// lister toutes les operations afin de calculer le chiffre d'affaire
	
	@Query("Select o from Operation o")
	public List<Operation> findall();
	
	
	
	
	
	
	
	

}
