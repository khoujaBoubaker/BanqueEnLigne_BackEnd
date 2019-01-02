package mbdemoapp.admins.services;



import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import mbdemoapp.domain.Administrateur;
import mbdemoapp.domain.Client;

@Service
public interface IAdministrateurService {
	
public Administrateur addadmin(Administrateur administrateur);
public List<Administrateur> findall();
public Administrateur findByLogin(String login);
public boolean authenticate(String login,String password);
public Administrateur findById(int id);

public void deleteAdministrateur(int id);



@Query("select a from Administrateur a where a.nom like :x")
public Page<Administrateur> chercher(@Param("x")String mc,Pageable pageable);


public Administrateur trouverAdmin(String username,String password);

public Administrateur chercherAdministrateurById(int id);

public Administrateur chercherAdminByUsername(String username);






}
