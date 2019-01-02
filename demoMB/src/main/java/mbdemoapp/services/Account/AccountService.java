package mbdemoapp.services.Account;

import mbdemoapp.domain.Administrateur;
import mbdemoapp.domain.Role;

public interface AccountService {
	
	public Administrateur saveAdmin(Administrateur admin);
	public Role saveRole(Role role);
	public void addRoleToUse(String userneme,String roleName);
	public Administrateur findUserByUsername(String username);

}
