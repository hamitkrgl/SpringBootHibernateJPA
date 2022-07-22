package com.bilgeadam.SpringBootRestHibernateJPA.repo;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bilgeadam.SpringBootRestHibernateJPA.model.Ogrenci;

@Repository
public interface OgrenciRepo extends JpaRepository<Ogrenci, Long> {
	public Ogrenci findOgrenciByNAME(String name);

	@Query(name = "findByOgrenciName", value = "SELECT * FROM obsh.ogrenci WHERE NAME LIKE :NAME", nativeQuery = true)
	public List<Ogrenci> findByOgrenciName(@Param("NAME") String name);

	// dervied query
	public List<Ogrenci> findAllByNAMELike(String expression, Sort sort);

	public List<Ogrenci> findAllByNAMELikeOrderByNAMEDesc(String name);
}
