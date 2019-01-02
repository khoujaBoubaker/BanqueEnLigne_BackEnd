package mbdemoapp.Controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mbdemoapp.agences.service.IAgenceService;
import mbdemoapp.clients.services.IClientService;
import mbdemoapp.conseillers.services.IConseillerService;
import mbdemoapp.domain.Administrateur;
import mbdemoapp.domain.Agence;
import mbdemoapp.domain.Client;
import mbdemoapp.domain.Conseiller;
import mbdemoapp.repo.ClientRepo;
import mbdemoapp.util.logs.ILogger;

@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping("/clients")
public class ClientController {
	
	// LOGGER
	
	@Autowired
	@Qualifier("console")
	ILogger logger;
	
	@Autowired
	private IClientService clientService;
	
	@Autowired
	private IAgenceService agenceService;
	
	@Autowired
	private IConseillerService conseillerService;
	
	@Autowired
	private ClientRepo repo;
	
	
	@RequestMapping(value="/client/{id}",method=RequestMethod.POST)
	public Client save(@RequestBody Client client,@PathVariable int id)
	    
	{   Agence agence=this.agenceService.findById(id);
		client.setDateDeCreation(new Date());
    	client.setAgence(agence);
		this.clientService.addClient(client);
		return client;
	}
	
	//suppression du client
	
	@RequestMapping(value="/client/delete/{id}",method=RequestMethod.DELETE)
	public Boolean delete(@PathVariable int id)
	{
		this.clientService.deleteClient(id);
		return true;
	}
	
	//mise a jour client
	

	@RequestMapping(value="/clientupdate/{id}",method=RequestMethod.POST)
	public Client update(@PathVariable int id,@RequestBody Client client)
	{
		int idagence=0;
		// dans le client REST ARC ON DONNE LES VALEURS
		// DE LA NOUVELLE ENTITE
		// SANS L ID AVEC BODY ET METHODE POST
		client.setIdclient(id);
		Client trouve=this.clientService.findClientById(id);
		client.setDateDeCreation(trouve.getDateDeCreation());
		client.setAgence(trouve.getAgence());
		this.clientService.addClient(client);
		//Conseiller con=clientbd.getConseiller();
		return client;

		}
		

	
	@RequestMapping(value = "/client/{id}", method = RequestMethod.GET)
    public Client getContact(@PathVariable int id) {
         return clientService.findClientById(id);
	}
	
	
	
	
	
	@RequestMapping(value="/chercherclients",method=RequestMethod.GET)
	public Page<Client> chercher(
			@RequestParam(name="mc",defaultValue="") String mc,
			@RequestParam(name="page",defaultValue="0")int page,
			@RequestParam(name="size",defaultValue="5")int size){
		
		return repo._chercherClientParMotCle("%"+mc+"%", new PageRequest(page, size));
		
	}
	
	
	@RequestMapping(value="/chercherclientsTriessParSalaire",method=RequestMethod.GET)
	public Page<Client> chercherClientsTries(
			@RequestParam(name="mc",defaultValue="") String mc,
			@RequestParam(name="page",defaultValue="0")int page,
			@RequestParam(name="size",defaultValue="5")int size){
		
		return clientService._chercherClientParMotCleEtParsalary("%"+mc+"%", page, size);
		
	}
	
	@RequestMapping(value="/chercherClientsTriessParSalaireOC",method=RequestMethod.GET)
	public Page<Client> chercherClientsTriesParSalire(
			@RequestParam(name="mc",defaultValue="") String mc,
			@RequestParam(name="page",defaultValue="0")int page,
			@RequestParam(name="size",defaultValue="5")int size)
	{
		return clientService._chercherClientParMotCleEtParSalaryParOrdreCroissant("%"+mc+"%", page, size);
	}
	
	// TRI CROISSANT DES CLIENTS PAR EMAIL
	@RequestMapping(value="/chercherClientsTriessParEmailOC",method=RequestMethod.GET)
	public Page<Client> chercherClientsTriesParEmailCroissant(
			@RequestParam(name="mc",defaultValue="") String mc,
			@RequestParam(name="page",defaultValue="0")int page,
			@RequestParam(name="size",defaultValue="5")int size){
		return this.clientService._chercherClientsParMotCleEtParEmailParOrdreCroissant("%"+mc+"%", page, size);
		
	}
	
	@RequestMapping(value="/chercherClientsTriessParEmailDEC",method=RequestMethod.GET)
	public Page<Client> chercherClientsTriesParEmailDecroissant(
			@RequestParam(name="mc",defaultValue="") String mc,
			@RequestParam(name="page",defaultValue="0")int page,
			@RequestParam(name="size",defaultValue="5")int size){
		return this.clientService.chercherCleintsParMotCleEtParEmailParOrdreDecroissant("%"+mc+"%", page, size);
		}
	
	
	@RequestMapping(value="/chercherclientsTriessPardtn",method=RequestMethod.GET)
	public Page<Client> chercherClientsTriesPardtn(
			@RequestParam(name="mc",defaultValue="") String mc,
			@RequestParam(name="page",defaultValue="0")int page,
			@RequestParam(name="size",defaultValue="5")int size){
		
		return clientService._chercherClientParMotCleEtPardtn("%"+mc+"%", page, size);
		
	}
	
	@RequestMapping(value="/chercherclientsTriessPardtc",method=RequestMethod.GET)
	public Page<Client> chercherClientsTriesPardtc(
			@RequestParam(name="mc",defaultValue="") String mc,
			@RequestParam(name="page",defaultValue="0")int page,
			@RequestParam(name="size",defaultValue="5")int size){
		
		return clientService._chercherClientParMotCleEtPardtn("%"+mc+"%", page, size);
		
	}
	
	@RequestMapping(value="/chercherclientsParAgence",method=RequestMethod.GET)
	public Page<Client> chercherConseillersParAgence(
			@RequestParam(name="mc") int id,
			@RequestParam(name="page",defaultValue="0")int page,
			@RequestParam(name="size",defaultValue="5")int size){
		
		return this.clientService._chercherClientsParAgence(id, page, size);
		
	}
	
	
	@RequestMapping(value="/chercherclientsPourVirement",method=RequestMethod.GET)
	public Page<Client> chercherClientsPourVirement(
			@RequestParam(name="idcl") int id,
			@RequestParam(name="page",defaultValue="0")int page,
			@RequestParam(name="size",defaultValue="5")int size){
		
		return this.clientService._chercherClientsPourVirement(id, page, size);
		
	}
	
	@RequestMapping(value="/virement",method=RequestMethod.GET)
	public Page<Client> findClientToVirement(
			@RequestParam(name="idcl") int id,
			@RequestParam(name="motCle",defaultValue="") String motCle,
			@RequestParam(name="page",defaultValue="0") int page,
			@RequestParam(name="size",defaultValue="10") int size)
	{
		logger.log("activation  du controleur");
		return this.clientService._chercherClientsPourVirement(id, motCle, page, size);
		
	}
	
	@RequestMapping(value="/size",method=RequestMethod.GET)
	public int sizeClients() {
		return this.clientService.sizeclient();
	}
	

	

}
