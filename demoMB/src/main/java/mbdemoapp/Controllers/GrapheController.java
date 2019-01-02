package mbdemoapp.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import mbdemoapp.graphes.Domain.Graphe;
import mbdemoapp.services.graphiques.IGrapheMetier;

@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping("/graphes")
public class GrapheController {
	
	@Autowired
	private IGrapheMetier grapheMetier;
	
	@RequestMapping(value="/chart/{id}",method=RequestMethod.GET)
	public Graphe listerGraphes(@PathVariable int id){
		return this.grapheMetier.graphesOperations(id);
		
	}
	
	@RequestMapping(value="/liste/{id}",method=RequestMethod.GET)
	public List<Graphe> listeGraphesPeriode(@PathVariable int id){
		return this.grapheMetier.graphesOperationsParPeriodeI(id);
	}
	
	

}
