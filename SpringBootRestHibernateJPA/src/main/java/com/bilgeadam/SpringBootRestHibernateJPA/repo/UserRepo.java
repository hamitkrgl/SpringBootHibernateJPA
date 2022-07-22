package com.bilgeadam.SpringBootRestHibernateJPA.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bilgeadam.SpringBootRestHibernateJPA.model.CustomUser;

@Repository
public interface UserRepo extends JpaRepository<CustomUser, Long> {

	public CustomUser findByusername(String username);

}
