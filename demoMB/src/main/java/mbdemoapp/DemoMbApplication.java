package mbdemoapp;




import static org.hamcrest.CoreMatchers.instanceOf;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.mockito.internal.matchers.Find;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.EnableLoadTimeWeaving;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javassist.expr.Instanceof;
import mbdemoapp.Credit.Services.IcreditMetier;
import mbdemoapp.Fournisseurs.Comptes.CleNumComptes.INumCompte;
import mbdemoapp.admins.services.IAdministrateurService;
import mbdemoapp.agences.service.IAgenceService;
import mbdemoapp.clients.services.IClientService;
import mbdemoapp.compteCourantSoldes.ICompteCourantUtil;
import mbdemoapp.comptes.services.ICompteService;
import mbdemoapp.comptesCourant.Services.CompteCourantsService;
import mbdemoapp.conseillers.services.IConseillerService;
import mbdemoapp.domain.Administrateur;
import mbdemoapp.domain.Agence;
import mbdemoapp.domain.Client;
import mbdemoapp.domain.Compt;

import mbdemoapp.domain.CompteCourant;
import mbdemoapp.domain.CompteEpargne;
import mbdemoapp.domain.Conseiller;
import mbdemoapp.domain.Credit;
import mbdemoapp.domain.Operation;
import mbdemoapp.domain.RendezVous;
import mbdemoapp.domain.Retraitt;
import mbdemoapp.domain.Role;
import mbdemoapp.rendezVous.Services.IRendezVousService;
import mbdemoapp.repo.AdminRepo;
import mbdemoapp.repo.ClientRepo;
import mbdemoapp.repo.ComptCourantRepo;
import mbdemoapp.repo.ComptRepo;
import mbdemoapp.services.Account.AccountService;
import mbdemoapp.services.BanqueMetier.IBanqueMetier;
import mbdemoapp.services.compteEpargnes.ICompteEargneService;
import mbdemoapp.services.graphiques.IGrapheMetier;
import mbdemoapp.services.mail.IMailSender;

import mbdemoapp.util.logs.ILogger;




@SpringBootApplication
@EnableAspectJAutoProxy
@Configuration
public class DemoMbApplication implements CommandLineRunner {
	
	//@Autowired
	//public JavaMailSender sender;
	
//	@Autowired
	//IMailSender mailSender;
	
	@Autowired
	public IGrapheMetier grapheMetier;
	
	@Autowired
	public INumCompte numcompteImpl;
	

	@Autowired
	IAgenceService agenceservice;
	
	@Autowired
	ICompteCourantUtil compteCourantutil;
	

	
	@Autowired
	CompteCourantsService courant;
	
	@Autowired
	ICompteEargneService epargneService;
	
	@Autowired
	ComptRepo comptrepo;
	
	@Autowired
	IClientService clientservice;
	
	@Autowired
	IAdministrateurService adminService;
	
	
	@Autowired
	IBanqueMetier banqueMetier;
	
	@Autowired
	ComptCourantRepo repo;
	
	@Autowired
	ClientRepo clientrepo;
	
    @Autowired
	private IConseillerService conseillerService;
    
    @Autowired
    IAdministrateurService service;
    
    @Autowired
	IAgenceService agenceService;
    
    @Autowired
	IConseillerService conseilservice;
    
    @Autowired
	IRendezVousService rendezVousService;
    

	@Bean
	public BCryptPasswordEncoder getBCPE() {
		return new BCryptPasswordEncoder();
	}
	
	

	public void setClientrepo(ClientRepo clientrepo) {
		this.clientrepo = clientrepo;
	}

	
	
	
	
	
	
	
	
	
	@Autowired
	private AccountService accountservice;
	
	@Autowired
	private IcreditMetier creditMetier;
	


	public static void main(String[] args) {
		SpringApplication.run(DemoMbApplication.class, args);
	}
	
	@Autowired
	private AdminRepo adminRepo;

	@Override
	public void run(String... arg0) throws Exception {
		
		System.out.println("chercher administrateur by username :"+this.adminRepo.findByUsername("admin"));
		
		ClassPathResource backImgFile=new ClassPathResource("images/imageprofile.jpg");
        byte[] arrayPic=new byte[(int) backImgFile.contentLength()];
	    backImgFile.getInputStream().read(arrayPic);
       
		// AJOUT ADMINISTRATEUR
		Administrateur administrateur=new Administrateur();
		administrateur.setUsername("admin");
		administrateur.setPassword("admin");
		administrateur.setPic(arrayPic);
		//adminService.addadmin(administrateur);
		
		
		// ajout client
		Client client=new Client();
		client.setNom("khouja");
		client.setPrenom("Boubaker");
		//this.clientservice.addClient(client);
		//System.out.println("total mensualité :"+(int)this.creditMetier.SimulerCreditAvecApportPersonnelAvecTauxFixe(20000.0,200000.0,48));
		
		//this.banqueMetier.AjouterCreditAunClient(1,new Credit());
		
		

		
		
		
		/*
		 * 
		 * System.out.println("Simulation credit sans apport ..");
		System.out.println(creditMetier.SimulerCredit(200000.0,240,4.5));
		
		System.out.println("Simulation credit avec apport ..");
		System.out.println(creditMetier.SimulerCreditAvecApportPersonnel(20000.0,200000.0,240,4.5));
		
		System.out.println("Simulation credit avec taux fixe");
		System.out.println(creditMetier.SimulerCreditAvecTauxFixe(20000.0,200000.0,240));
		 * 
		 */
		
		 
		
	
		
	    	/*
	    	 * 
	    	 * 
	    	 * 
	    	 *  System.out.println("credit avec taux fixe et apport");
		  System.out.println(creditMetier.SimulerCreditAvecApportPersonnelAvecTauxFixe(20000.0,200000.0,240));
		  
		  System.out.println("cout total du crédit");
		  System.out.println(creditMetier.coutTotalCreditAvecTauxFixe(20000.0,200000.0,240));
		  
		  
			ClassPathResource backImgFile=new ClassPathResource("images/imageprofile.jpg");
	        byte[] arrayPic=new byte[(int) backImgFile.contentLength()];
		    backImgFile.getInputStream().read(arrayPic);
		    Administrateur admin=new Administrateur();
		    admin.setPic(arrayPic);
		    admin.setNom("KHOUJA");
		    admin.setPoste("Ingénieur");
		    admin.setAddresse("MARSA , 2 rue oued mellegue");
		    admin.setUsername("pass");
		    admin.setPassword("pass");
	    	adminService.addadmin(admin);
	    	 * 
	    	 * 
	    	 */
		
		
		
}
}
