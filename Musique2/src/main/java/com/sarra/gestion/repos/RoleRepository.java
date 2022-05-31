package com.sarra.gestion.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.sarra.gestion.entities.Role;

@RepositoryRestResource(path = "rest")
public interface RoleRepository extends JpaRepository<Role, Long> {

}
