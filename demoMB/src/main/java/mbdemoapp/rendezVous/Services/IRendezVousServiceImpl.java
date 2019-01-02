package mbdemoapp.rendezVous.Services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import mbdemoapp.clients.services.IClientService;
import mbdemoapp.conseillers.services.IConseillerService;
import mbdemoapp.domain.Agence;
import mbdemoapp.domain.Client;
import mbdemoapp.domain.Conseiller;
import mbdemoapp.domain.RendezVous;
import mbdemoapp.repo.AgenceRepository;
import mbdemoapp.repo.RendezVousRepository;

@Service
public class IRendezVousServiceImpl implements IRendezVousService {
	
	// injection du repository
	@Autowired
	private RendezVousRepository repo;
	
	@Autowired
	private IClientService clientService;
	
	@Autowired
	private AgenceRepository agencerepo;
	
	@Autowired
	private IConseillerService conseillerService;

	@Override
	public void addRendezVous(RendezVous rendezVous) {
		// TODO Auto-generated method stub
		this.repo.save(rendezVous);
		
	}

	@Override
	public void deleteRendeVous(int id) {
		// TODO Auto-generated method stub
		this.repo.delete(id);
		
	}

	public RendezVous findRendeVousById(int id) {
		// TODO Auto-generated method stub
		return this.repo.findOne(id);
	}

	@Override
	public Page<RendezVous> _listerRendezVous(int page, int size) {
		// TODO Auto-generated method stub
		return this.repo.listeRendezVous(new PageRequest(page, size));
	}

	@Override
	public Boolean addRendezVousClientConseiller(RendezVous rendezVous, int idClient, int idConseiller) {
		// TODO Auto-generated method stub
		Client client=this.clientService.findClientById(idClient);
		Conseiller conseiller=this.conseillerService.findConseillerById(idConseiller);
		rendezVous.setClient(client);
		rendezVous.setConseiller(conseiller);
		this.repo.save(rendezVous);
		return true;
	}

	@Override
	public Page<RendezVous> _listerRendezVousParClient(int idClient,String motCle, int page, int size) {
		// TODO Auto-generated method stub
		return this.repo.listeRendezVousParClient(idClient, "%"+motCle+"%",new PageRequest(page, size));
	}

	@Override
	public Boolean supprimerRendezVous(int idRendezVous) {
		// TODO Auto-generated method stub
	 this.repo.delete(idRendezVous);
	 return true;
	}

	@Override
	public RendezVous chercherRendezVousParId(int idRendezVous) {
		// TODO Auto-generated method stub
		return this.repo.findOne(idRendezVous);
	}


	public List<Conseiller> getAgence(int id) {
		// TODO Auto-generated method stub
		return this.agencerepo.findOne(id).getConseillers();
	}

	//@Override
	//public List<Conseiller> ConseillersDisponibles(Date jour, Date heure, int idagence) {
		// TODO Auto-generated method stub
		List<Conseiller> conseillersDispo=new ArrayList<Conseiller>();
	//}

	@Override
	public Boolean Disponible(int idConseiller,String jour,String heure) {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdfh=new SimpleDateFormat("hh:mm");
		
		// TODO Auto-generated method stub
		Boolean disponible=true;
		Conseiller conseiller=this.conseillerService.findConseillerById(idConseiller);
		for(RendezVous rdv:conseiller.getRendezVous())
		{
			
				
					try {
						if(rdv.getDateRendezVous().equals(sdf.parse(jour)) && rdv.getHeureRendezVous().equals(sdfh.parse(heure)))
							disponible=false;
						
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
			
		}
		return disponible;


	}

	@Override
	public List<Conseiller> chercherConseillersDisponibles(int idagence, String date, String heure) {
		// TODO Auto-generated method stub
		List<Conseiller> conseillersDisponible=new ArrayList<Conseiller>();
		Agence agence=this.agencerepo.findOne(idagence);
		for(Conseiller conseiller:agence.getConseillers())
			if (this.Disponible(conseiller.getId(),date, heure)&&(conseillersDisponible.indexOf(conseiller)<0))
				conseillersDisponible.add(conseiller);
         return conseillersDisponible;
	}

	@Override
	public List<RendezVous> findAllRendezVous() {
		// TODO Auto-generated method stub
		return this.repo.findAll();
	}


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
