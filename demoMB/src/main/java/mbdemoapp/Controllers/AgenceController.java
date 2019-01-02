package mbdemoapp.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mbdemoapp.agences.service.IAgenceService;
import mbdemoapp.domain.Administrateur;
import mbdemoapp.domain.Agence;
import mbdemoapp.repo.AgenceRepository;



@RestController
@CrossOrigin(origins="http://localhost:4200")  // permettre à la partie front d'interagir
@RequestMapping(value="/agences")
public class AgenceController {
	
	@Autowired
	private IAgenceService agenceService;
	
	/*  ### liste de toutes les agences
	 * 
	 */

	@Autowired
	private AgenceRepository repoagence;
	
	
	/* ###  AJOUT D'UNE NOUVELLE AGENCE
	 */
	
	@RequestMapping(value="/agence",method=RequestMethod.POST)
	public Agence save(@RequestBody Agence agence)
	{   this.agenceService.addagence(agence);
		return agence;
	}
	
	
	/*
	 *  ### retrouver une agence par son ID
	 */
	
	@RequestMapping(value="/Agence/{id}",method=RequestMethod.GET)
	public Agence findAgenceById(@PathVariable int id){
		return agenceService.findById(id);
		
	}
	
	
	/*
	 * ## Mise a jour d'une agence
	 */

	@RequestMapping(value="/agence/{id}",method=RequestMethod.PUT)
	public Agence update(@PathVariable int id,@RequestBody Agence agence)
	{
		// dans le client REST ARC ON DONNE LES VALEURS
		// DE LA NOUVELLE ENTITE
		// SANS L ID AVEC BODY ET METHODE POST
		agence.setIdagence(id);
		agenceService.addagence(agence);
		return agence;
	}
	
	/*
	 *  ## suppression agence
	 */
	
	@RequestMapping(value="/agence/delete/{id}",method=RequestMethod.DELETE)
	public Boolean delete(@PathVariable int id)
	{
		this.agenceService.delete(id);
		return true;
	}
	
	
	@RequestMapping(value="/chercherAgences",method=RequestMethod.GET)
	public Page<Agence> chercher(
			@RequestParam(name="mc",defaultValue="") String mc,
			@RequestParam(name="page",defaultValue="0")int page,
			@RequestParam(name="size",defaultValue="5")int size){
		
		return repoagence._chercherAgenceParMotCle("%"+mc+"%", new PageRequest(page, size));
		
	}
	
	
	@RequestMapping(value="/chercherAgencesTriesParCmmune",method=RequestMethod.GET)
	public Page<Agence> chercherParTriCommune(
			@RequestParam(name="mc",defaultValue="") String mc,
			@RequestParam(name="page",defaultValue="0")int page,
			@RequestParam(name="size",defaultValue="5")int size){
		
		return repoagence._chercherAgenceParMotCleTriesParCommune("%"+mc+"%", new PageRequest(page, size));
		
	}
	
	
	
	

}
