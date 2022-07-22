package com.bilgeadam.SpringBootRestHibernateJPA.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Ders {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long ID;

	// bir ogretmen birden fazla ders
	@ManyToOne // (cascade = CascadeType.MERGE)
	// DB tarafında garantilemek için
	// @OnDelete(action = OnDeleteAction.CASCADE)
	private Ogretmen ogretmen;

	@ManyToOne // (cascade = CascadeType.MERGE)
	// @OnDelete(action = OnDeleteAction.CASCADE)
	private Konu konu;

	@JsonBackReference
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "ders", cascade = { CascadeType.REMOVE, CascadeType.REFRESH })
	private Set<DersOgrenci> dersOgrenciler = new HashSet<DersOgrenci>();

	public Ders(Ogretmen ogretmen, Konu konu) {
		this.ogretmen = ogretmen;
		this.konu = konu;
	}

//	@Override
//	public String toString()
//	{
//		return "Ders [ID=" + ID + ", ogretmen=" + ogretmen + ", konu=" + konu + "]";
//	}

}
