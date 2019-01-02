package mbdemoapp.agences.service;

import java.util.List;

import org.springframework.stereotype.Service;

import mbdemoapp.domain.Agence;

@Service
public interface IAgenceService {
	
	public Agence addagence(Agence agence);
	public List<Agence> allagences();
	public Agence findByAddresse(String addresse);
	public Agence findById(int id);
	public void delete(int id);
	void edit(Agence agence);
	
	

}
