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
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Ogretmen {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long ID;

	@Column(length = 100)
	private String NAME;

	private boolean ISGICIK;

	// mecbur değilim, ama öğretmen içinden direkt olarak dersleri alabilmek için
	// tanımladım
	// mappedBy olmazsa N-N ilişkisi ile yeni tablo oluşturur
	// fetch default olarak lazy 'dir
	@JsonBackReference
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "ogretmen", cascade = { CascadeType.REMOVE, CascadeType.REFRESH })
	private Set<Ders> dersler = new HashSet<Ders>();

	public Ogretmen(String nAME, boolean iS_GICIK) {
		NAME = nAME;
		ISGICIK = iS_GICIK;
	}

//	@Override
//	public String toString()
//	{
//		return "Ogretmen [ID=" + ID + ", NAME=" + NAME + ", IS_GICIK=" + IS_GICIK + ", dersler=" + dersler.size() + "]";
//	}
}
