package mbdemoapp.Controllers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mbdemoapp.domain.Agence;
import mbdemoapp.domain.Operation;
import mbdemoapp.domain.Retraitt;
import mbdemoapp.services.BanqueMetier.IBanqueMetier;

@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping("/operations")
public class OperationController {
	
	@Autowired
	private IBanqueMetier banqueMetier;
	
	@RequestMapping(value="/chercherOperations/",method=RequestMethod.GET)
	public Page<Operation> chercherOperations(
			@RequestParam(name="idcompte") int idcompte,
			@RequestParam(name="page",defaultValue="0")int page,
			@RequestParam(name="size",defaultValue="5")int size){
		    return banqueMetier.listerOperations(idcompte, page, size);
	}
	
	
	@RequestMapping(value="/chercherRetraits/",method=RequestMethod.GET)
	public Page<Operation> chercherRetraits(
			@RequestParam(name="idcompte") int idcompte,
			@RequestParam(name="page",defaultValue="0")int page,
			@RequestParam(name="size",defaultValue="5")int size){
		    return banqueMetier.listerRetraits(idcompte, page, size);
	}
	
	@RequestMapping(value="/chercherVersements/")
	public Page<Operation> chercherVersements(
			@RequestParam(name="idcompte") int idcompte,
			@RequestParam(name="page",defaultValue="0") int page,
			@RequestParam(name="size",defaultValue="5") int size){
		return banqueMetier.listerVersements(idcompte, page, size);
	}
	
	
	@RequestMapping(value="/chercherOperationsParDate/",method=RequestMethod.GET)
	public Page<Operation> chercherOperationsParDate(
			@RequestParam(name="idcompte") int idcompte,
			@RequestParam(name="opdate") String date,
			@RequestParam(name="page",defaultValue="0")int page,
			@RequestParam(name="size",defaultValue="5")int size) throws ParseException{
		     DateFormat sdFormat=new SimpleDateFormat("yyyy-MM-dd");
		    return banqueMetier.listerOperationsParDate(idcompte,sdFormat.parse(date), page, size);
	}
	
	@RequestMapping(value="/retrait/{id}",method=RequestMethod.POST)
	public Boolean retirer(@PathVariable int id,@RequestBody Retraitt retrait) {
		double montantRetrait=retrait.getMontant();
		String libelle=retrait.getLibelle();
		this.banqueMetier.retirer(id,montantRetrait,libelle);
		return true;
	}
	
	
	@RequestMapping(value="/versement/{id}",method=RequestMethod.POST)
	public Boolean verserArgent(@PathVariable int id,@RequestBody double montant) {
		this.banqueMetier.verser(id, montant,"versement simple");
		return true;
	}
	
	
	@RequestMapping(value="/versementDansAgence/{id}",method=RequestMethod.POST)
	public Boolean verserArgentDansUneAgence(@PathVariable int id,@RequestBody double montant ) {
		this.banqueMetier.retirer(id, montant,"Versement");
		return true;
	}
	
	
	

	
	@RequestMapping(value="/supprimerOperation/{id}",method=RequestMethod.DELETE)
	public Boolean supprimerOperation(@PathVariable int id) {
		this.banqueMetier.deleteOperation(id);
		return true;
	}
	
	// Utilisée au niveau du controleur
	// retrait d'une agence 
	@RequestMapping(value="/RetraitDepuisAgence/",method=RequestMethod.POST)
	public Boolean RetirerDuneAgence(
			@RequestParam(name="idcompte") int idcompte,
			@RequestParam(name="idagence")int idagence,
			@RequestParam(name="montant")double montant,
			@RequestParam(name="libelle",defaultValue="simple retrait") String libelle){
		    this.banqueMetier.RetirerDuneAgence(idcompte, montant,libelle,idagence);
		    return true;
	}
	
	// Utilisée au niveau du front pour régler mensualité
	
	@RequestMapping(value="/PayerMensualite/",method=RequestMethod.POST)
	public Boolean RetirerDuneAgencePourPayerMensualite(
			@RequestParam(name="idcompte") int idcompte,
			@RequestParam(name="idcredit") int idcredit,
			@RequestParam(name="idagence") int idagence,
			@RequestParam(name="montant") double montant,
			@RequestParam(name="libelle") String libelle) {
		this.banqueMetier.RetirerDuneAgencePourPayerMensualite(idcompte, montant, libelle, idagence, idcredit);
		return true;
		
	}
	
	
	@RequestMapping(value="/VerserDansUneAgence/",method=RequestMethod.POST)
	public Boolean VerserDansuneAgence(
			@RequestParam(name="idcompte") int idcompte,
			@RequestParam(name="idagence") int idagence,
			@RequestParam(name="montant") double montant,
			@RequestParam(name="libelle",defaultValue="versement simple") String libelle){
		    this.banqueMetier.verserDansUneAgence(idcompte, montant,libelle, idagence);
		    return true;
	}
	
	@RequestMapping(value="/VirementDansUneAgence/",method=RequestMethod.POST)
	public Boolean VirementDeCompteACompte(
			@RequestParam(name="idcompteEmetteur") int idC1,
			@RequestParam(name="idcompteCrediteur") int idC2,
			@RequestParam(name="idagence") int idagence,
			@RequestParam(name="montant") double montant,
			@RequestParam(name="libelle") String libelle) {
		this.banqueMetier.virement(idC1, idC2, montant,libelle,idagence);
		return true;
		
	}
	
	
	
	
	
	
	

	

}
