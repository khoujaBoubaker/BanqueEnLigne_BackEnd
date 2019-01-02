package mbdemoapp.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mbdemoapp.Credit.Services.IcreditMetier;
import mbdemoapp.clients.services.IClientService;
import mbdemoapp.domain.Client;
import mbdemoapp.domain.Credit;
import mbdemoapp.services.BanqueMetier.IBanqueMetier;

@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping("/credits")
public class CreditController {
	
	@Autowired
	private IcreditMetier creditMetier;
	
	@Autowired
	private IClientService clientService;
	
	
	
	
	
	@Autowired
	private IBanqueMetier banqueMetier;
	
	@RequestMapping(value="/credit",method=RequestMethod.GET)
	public double SimulerCredit(
			@RequestParam(name="apport",defaultValue="0") double apport,
			@RequestParam(name="total",defaultValue="0") double TotalCredit,
			@RequestParam(name="duree") int duree) {
		return this.creditMetier.SimulerCreditAvecApportPersonnelAvecTauxFixe(apport,TotalCredit, duree);
		
	}
	
	
	@RequestMapping(value="/Coutcredit",method=RequestMethod.GET)
	public double CoutCredit(
			@RequestParam(name="apport",defaultValue="0") double apport,
			@RequestParam(name="total",defaultValue="0") double TotalCredit,
			@RequestParam(name="duree") int duree) {
		return this.creditMetier.coutTotalCreditAvecTauxFixe(apport,TotalCredit, duree);
		
	}
	
	
	
	
	
	@RequestMapping(value="/AddCredit/{id}",method=RequestMethod.POST)
	public Credit AddCreditToClient(@RequestBody Credit credit,@PathVariable int id) {
		//System.out.println(credit);
		Credit credi=new Credit();
		credi.setApport(credit.getApport());
		credi.setDuree(credit.getDuree());
		credi.setMontantemprunte(credit.getMontantemprunte());
		credi.setMontantResteApayer(this.creditMetier.coutTotalCreditAvecTauxFixe(credit.getApport(),credit.getMontantemprunte(),credit.getDuree()));
		System.out.println(credi);
		this.banqueMetier.AjouterCreditAunClient(id, credi);
		
		return credi;
		
	}
	
	@RequestMapping(value="/creditsParClient",method=RequestMethod.GET)
	public Page<Credit> chercherCreditParClient(
			@RequestParam(name="idcl") int id,
			@RequestParam(name="page",defaultValue="0")int page,
			@RequestParam(name="size",defaultValue="5") int size){
		return this.creditMetier.chercherCreditsParClient(id, page, size);
		
	}
	
	@RequestMapping(value="/credit/delete/{id}",method=RequestMethod.DELETE)
	public Boolean supprimerCredit(@PathVariable int id) {
		this.banqueMetier.SupprimerCredit(id);
		return true;
		
	}
	
	@RequestMapping(value="/credit/MiseAjour/{id}",method=RequestMethod.PUT)
	public Credit MettreAjourCredit(@RequestBody Credit credit,@PathVariable int id) {
		
		Client client=this.clientService.findClientById(id);
		credit.setClient(client);
		this.banqueMetier.ajouterCredit(credit);
		return credit;
		
	}
	
	
	
	
	

}
