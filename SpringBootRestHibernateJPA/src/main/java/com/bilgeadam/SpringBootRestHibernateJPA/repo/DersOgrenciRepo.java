package com.bilgeadam.SpringBootRestHibernateJPA.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bilgeadam.SpringBootRestHibernateJPA.model.DersOgrenci;

@Repository
public interface DersOgrenciRepo extends JpaRepository<DersOgrenci, Long> {

}
