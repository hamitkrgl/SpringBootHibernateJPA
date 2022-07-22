package com.bilgeadam.SpringBootRestHibernateJPA.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bilgeadam.SpringBootRestHibernateJPA.model.Ders;

public interface DersRepo extends JpaRepository<Ders, Long> {

}
