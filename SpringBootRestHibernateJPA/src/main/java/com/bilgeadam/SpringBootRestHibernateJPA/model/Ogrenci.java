package com.bilgeadam.SpringBootRestHibernateJPA.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Ogrenci {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long ID;

	// @Column()
	private String NAME;

	@Column(unique = true)
	private long OGRNUMBER;

	private long YEAR;

	@JsonBackReference
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "ders", cascade = { CascadeType.REMOVE, CascadeType.REFRESH })
	private Set<DersOgrenci> ogrenciDersler = new HashSet<DersOgrenci>();

	public Ogrenci(String nAME, long oGR_NUMBER, long yEAR) {
		NAME = nAME;
		OGRNUMBER = oGR_NUMBER;
		YEAR = yEAR;
	}

//	@Override
//	public String toString()
//	{
//		return "Ogrenci [ID=" + ID + ", NAME=" + NAME + ", OGR_NUMBER=" + OGR_NUMBER + ", YEAR=" + YEAR + "]";
//	}

}
