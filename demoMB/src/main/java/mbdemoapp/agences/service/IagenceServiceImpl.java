package mbdemoapp.agences.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import mbdemoapp.domain.Agence;
import mbdemoapp.repo.AgenceRepository;
import mbdemoapp.util.logs.ILogger;

@Service
public class IagenceServiceImpl implements IAgenceService {
	
	@Autowired
	private AgenceRepository agencerepository;
	
	

	public AgenceRepository getAgencerepository() {
		return agencerepository;
	}

	public void setAgencerepository(AgenceRepository agencerepository) {
		this.agencerepository = agencerepository;
	}

	@Override
	public Agence addagence(Agence agence) {
		// TODO Auto-generated method stub
		
		return agencerepository.save(agence);
	}

	@Override
	public List<Agence> allagences() {
		// TODO Auto-generated method stub
		return (List<Agence>) agencerepository.findAll();
	}

	@Override
	public Agence findByAddresse(String addresse) {
		// TODO Auto-generated method stub
		return agencerepository.findByAddresse(addresse);
	}

	@Override
	public Agence findById(int id) {
		// TODO Auto-generated method stub
		return agencerepository.findOne(id);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		agencerepository.delete(this.findById(id));
		
	}

	@Override
	public void edit(Agence agence) {
		// TODO Auto-generated method stub
		agencerepository.save(agence);
		
	}

}
