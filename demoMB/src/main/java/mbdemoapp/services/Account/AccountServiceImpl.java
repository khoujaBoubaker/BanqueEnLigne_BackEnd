package mbdemoapp.services.Account;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import mbdemoapp.domain.Administrateur;
import mbdemoapp.domain.Role;
import mbdemoapp.repo.AdminRepo;
import mbdemoapp.repo.RoleRepository;


@Service
@Transactional
public class AccountServiceImpl implements AccountService {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private AdminRepo adminrepository;
	
	@Autowired
	private RoleRepository rolerepository;
	
	@Override
	public Administrateur saveAdmin(Administrateur admin) {
		// TODO Auto-generated method stub
		String hashPW=bCryptPasswordEncoder.encode(admin.getPassword());
		admin.setPassword(hashPW);
		return adminrepository.save(admin);
	}

	@Override
	public Role saveRole(Role role) {
		// TODO Auto-generated method stub
		return rolerepository.save(role);
	}

	@Override
	public void addRoleToUse(String username, String roleName) {
		// TODO Auto-generated method stub
		Role role=rolerepository.findByRoleName(roleName);
		Administrateur admin=adminrepository.findByUsername(username);
		
		admin.getRoles().add(role);
	}

	@Override
	public Administrateur findUserByUsername(String username) {
		// TODO Auto-generated method stub
		return adminrepository.findByUsername(username);
	}

}
