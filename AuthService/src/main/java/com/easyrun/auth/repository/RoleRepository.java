package com.easyrun.auth.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.easyrun.auth.model.Role;
@Repository
public interface RoleRepository extends MongoRepository<Role, String> {
	
	List<Role> getRolesByIdIn(List<String> ids);
	
	Role getByRoleCd(String roleCd);
}
