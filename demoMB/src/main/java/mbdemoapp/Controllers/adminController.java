package mbdemoapp.Controllers;


import java.security.Principal;
import java.util.List;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



import org.springframework.web.bind.annotation.*;

import mbdemoapp.admins.services.IAdministrateurService;
import mbdemoapp.domain.Administrateur;
import mbdemoapp.repo.AdminRepo;


@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping(value="/admins")
public class adminController {
	@Autowired
	private IAdministrateurService adminservice;
	
	@RequestMapping(value="/admin",method=RequestMethod.GET)
	public Administrateur getAdministrateur(
			@RequestParam(name="username") String username,
			@RequestParam(name="password") String password) {
		return this.adminservice.trouverAdmin(username, password);
	}
	
	
	@RequestMapping(value="/getAdmin",method=RequestMethod.GET)
	public Administrateur getAdministrateurBy(
			@RequestParam(name="username") String username) {
		return this.adminservice.chercherAdminByUsername(username);
		
	}
	
	
	@Autowired
	private AdminRepo repo;
	
	public void setRepo(AdminRepo repo) {
		this.repo = repo;
	}


	public void setAdminservice(IAdministrateurService adminservice) {
		this.adminservice = adminservice;
	}
	
	@RequestMapping(value="/",method=RequestMethod.GET)
	public Response get() {
		System.out.println("build");
		return Response.ok().build();
		
	}
	
	
	// SAVE NEW CONTACT
	
	@RequestMapping(value="/user",method=RequestMethod.POST)
	public Administrateur save(@RequestBody Administrateur administrateur)
	{
		adminservice.addadmin(administrateur);
		return administrateur;
	}

	@RequestMapping(value = "/user", method = RequestMethod.GET)
    public List<Administrateur> listAllUsers() {
         return adminservice.findall();
	}
	
	// RECHERCHE PAR ID
	// administarteurs
	
	@RequestMapping(value = "/findadmin/{id}", method = RequestMethod.GET)
    public Administrateur getContact(@PathVariable int id) {
         return adminservice.chercherAdministrateurById(id);
	}
	
	// SUPPRESSION D'UN ADMINISTRATEUR
	// 
	
	@RequestMapping(value="/user/delete/{id}",method=RequestMethod.DELETE)
	public Boolean delete(@PathVariable int id)
	{
		adminservice.deleteAdministrateur(id);
		return true;
	}
	
	
	// MISE A JOUR 
	// DE L ENTITE PERSISTENTE
    // 
	
	@RequestMapping(value="/user/{id}",method=RequestMethod.PUT)
	public Administrateur update(@PathVariable int id,@RequestBody Administrateur administrateur)
	{
		// dans le client REST ARC ON DONNE LES VALEURS
		// DE LA NOUVELLE ENTITE
		// SANS L ID AVEC BODY ET METHODE POST
		administrateur.setId(id);
		return adminservice.addadmin(administrateur);
	}
	
	
	@RequestMapping(value="/chercherContacts",method=RequestMethod.GET)
	public Page<Administrateur> chercher(
			@RequestParam(name="mc",defaultValue="") String mc,
			@RequestParam(name="page",defaultValue="0")int page,
			@RequestParam(name="size",defaultValue="5")int size){
		
		return repo._chercher("%"+mc+"%", new PageRequest(page, size));
		
	}
	

	
	
	
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public Principal user(Principal principal) {
		System.out.println("user logge"+principal);
		return principal;
		
		
	}
	
	
	
	
	
	
	
}


