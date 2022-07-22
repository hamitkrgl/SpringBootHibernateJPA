package com.bilgeadam.SpringBootRestHibernateJPA.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Konu {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long ID;

	@Column(length = 200)
	private String NAME;

	public Konu(String nAME) {
		NAME = nAME;
	}

//	@Override
//	public String toString()
//	{
//		return "Konu [ID=" + ID + ", NAME=" + NAME + "]";
//	}
}
