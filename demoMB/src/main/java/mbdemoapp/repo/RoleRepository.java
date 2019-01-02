package mbdemoapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import mbdemoapp.domain.Role;

public interface RoleRepository extends JpaRepository<Role,Integer> {

public Role findByRoleName(String roleName);


}
