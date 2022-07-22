package com.bilgeadam.SpringBootRestHibernateJPA.repo;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bilgeadam.SpringBootRestHibernateJPA.model.Konu;

@Repository
public interface KonuRepo extends JpaRepository<Konu, Long> {
	public Konu findKonuByNAME(String name);

	@Query(name = "findByKonuName", value = "SELECT * FROM obsh.Konu WHERE NAME LIKE :NAME", nativeQuery = true)
	public List<Konu> findByKonuName(@Param("NAME") String name);

	// dervied query
	public List<Konu> findAllByNAMELike(String expression, Sort sort);

	public List<Konu> findAllByNAMELikeOrderByNAMEDesc(String name);
}
