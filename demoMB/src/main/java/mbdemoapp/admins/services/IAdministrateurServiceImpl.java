package mbdemoapp.admins.services;



import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;


import mbdemoapp.domain.Administrateur;
import mbdemoapp.domain.Client;
import mbdemoapp.repo.AdminRepo;
import mbdemoapp.util.logs.ILogger;


@Service 
public class IAdministrateurServiceImpl implements IAdministrateurService  {
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private AdminRepo adminrepo;

	@Override
	public Administrateur addadmin(Administrateur administrateur) {
		// TODO Auto-generated method stub
		String hashPW=bCryptPasswordEncoder.encode(administrateur.getPassword());
		administrateur.setPassword(hashPW);
		adminrepo.save(administrateur);
		return administrateur;
	}

	@Override
	public List<Administrateur> findall() {
		// TODO Auto-generated method stub
		return (List<Administrateur>) adminrepo.findAll();
	}



	@Override
	public boolean authenticate(String login, String password) {
		// TODO Auto-generated method stub
		Administrateur admin=this.findByLogin(login);
		if (!(admin==null)) {
			if (admin.getPassword().equals(password)) {
				return Boolean.TRUE;
			}
		}
		return Boolean.FALSE;
	}

	@Override
	public Administrateur findById(int id) {
		// TODO Auto-generated method stub
		return adminrepo.findOne(id);
	}

	@Override
	public void deleteAdministrateur(int id) {
		// TODO Auto-generated method stub
		adminrepo.delete(this.findById(id));
		
	}



	@Override
	public Page<Administrateur> chercher(String mc, Pageable pageable) {
		// TODO Auto-generated method stub
		return adminrepo._chercher(mc,pageable);
	}



	@Override
	public Administrateur findByLogin(String login) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Administrateur trouverAdmin(String username,String password) {
		// TODO Auto-generated method stub
		
		Administrateur admin= this.adminrepo.findByUsername(username);
		if(bCryptPasswordEncoder.matches(password,admin.getPassword()))
			return admin;
		return admin;
		
		
	}

	@Override
	public Administrateur chercherAdministrateurById(int id) {
		// TODO Auto-generated method stub
		return this.adminrepo.findOne(id);
	}

	@Override
	public Administrateur chercherAdminByUsername(String username) {
		// TODO Auto-generated method stub
		return this.chercherAdminByUsername(username);
	}

	
	


	
}
