package ma.marnissiabdelkarim.ecommebackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ma.marnissiabdelkarim.ecommebackend.entities.RoleEntity;

@Repository
public  interface RoleRepository extends JpaRepository<RoleEntity,Long> {
	RoleEntity findByRoleName(String rolename);

}
