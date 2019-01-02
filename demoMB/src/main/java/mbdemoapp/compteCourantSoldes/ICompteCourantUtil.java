package mbdemoapp.compteCourantSoldes;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import mbdemoapp.domain.CompteCourant;
import mbdemoapp.domain.CompteEpargne;

public interface ICompteCourantUtil extends CrudRepository<CompteCourant,Integer> {
	
	@Query("select c from CompteCourant c where c.client.idclient=:x ")
	public List<CompteCourant> listerCompteCourantParIdClient(@Param("x") int idClient);
	
	@Query("select c from CompteEpargne c where c.client.idclient=:x")
	public List<CompteEpargne> listerComptesEpargnesParIdClient(@Param("x")int idClient);
	
	
}
