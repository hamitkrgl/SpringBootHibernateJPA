package com.bilgeadam.SpringBootRestHibernateJPA.repo;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bilgeadam.SpringBootRestHibernateJPA.model.Ogretmen;

@Repository
public interface OgretmenRepo extends JpaRepository<Ogretmen, Long>
{
	// dervied query
	// select * from obsh.ogretmen ogretmen0_ where ogretmen0_.name=?
	public Ogretmen findOgretmenByNAME(String name);

	@Query(name = "findByOgretmenName", value = "SELECT * FROM obsh.ogretmen WHERE NAME LIKE :NAME", nativeQuery = true)
	public List<Ogretmen> findByOgretmenName(@Param("NAME") String name);

	// dervied query
	public List<Ogretmen> findAllByNAMELike(String expression, Sort sort);

	public List<Ogretmen> findAllByNAMELikeOrderByNAMEDesc(String name);
}