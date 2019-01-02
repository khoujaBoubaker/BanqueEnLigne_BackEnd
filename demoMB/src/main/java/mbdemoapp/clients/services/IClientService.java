package mbdemoapp.clients.services;



import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import mbdemoapp.domain.Client;
import mbdemoapp.domain.CompteCourant;


@Service
public interface IClientService {
	
	public Client addClient(Client client);
	public List<Client> getallclients();
	public Client findClientById(int id);
	public Client findClientByName(String nom);
	public Client findClientByEmail(String email);
	public void deleteClient(int idclient);
	public void saveClientInAgence(int idagence,Client client);
	public void ajouterCompteCourantAunClient(int idclient,CompteCourant cc);
	public Page<Client> _chercherClientParMotCleEtParsalary(String mc,int page,int size);
	public Page<Client> _chercherClientParMotCleEtParSalaryParOrdreCroissant(String mc,int page,int size);
	public Page<Client> _chercherClientsParMotCleEtParEmailParOrdreCroissant(String mc,int page,int size);
	public Page<Client> chercherCleintsParMotCleEtParEmailParOrdreDecroissant(String mc,int page,int size);
	public Page<Client> _chercherClientParMotCleEtPardtn(String mc,int page,int size);
	public Page<Client> _chercherClientParMotCleEtPardtc(String mc,int page,int size);
	public Page<Client> _chercherClientsParAgence(int idagence,int page,int size);
	public Page<Client> _chercherClientsPourVirement(int idClient,int page,int size);
	public Page<Client> _chercherClientsPourVirement(int idclient,String motCle,int page,int size);
	

	public int sizeclient();
	
	
	
	
	
	
	
	
	
		
	
	

}
