package com.sarra.gestion.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.sarra.gestion.entities.User;



@RepositoryRestResource(path = "rest")
public interface UserRepository extends JpaRepository<User, Long> {
User findByUsername (String username);

}