package mbdemoapp.rendezVous.Services;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import mbdemoapp.domain.Agence;
import mbdemoapp.domain.Conseiller;
import mbdemoapp.domain.RendezVous;
import mbdemoapp.repo.RendezVousRepository;

@Service
public interface IRendezVousService {
	
  public void addRendezVous(RendezVous rendezVous);
  public void deleteRendeVous(int id);
  public RendezVous findRendeVousById(int id);
  
  public Boolean addRendezVousClientConseiller(RendezVous rendezVous,int idClient,int idConseiller);
  
  // GET ALL RENDEZ-VOUS
  
  public Page<RendezVous> _listerRendezVous(int page,int size);
  
  public Page<RendezVous> _listerRendezVousParClient(int idClient,String motcle,int page,int size);
  
  public Boolean supprimerRendezVous(int idRendezVous);
  public RendezVous chercherRendezVousParId(int idRendezVous);
  
  
  public List<Conseiller> getAgence(int idClient);
  
  public List<RendezVous> findAllRendezVous();
  

  
  public Boolean Disponible(int idConseiller,String jour,String heure);
  
  public List<Conseiller> chercherConseillersDisponibles(int idagence,String date,String heure);

}
