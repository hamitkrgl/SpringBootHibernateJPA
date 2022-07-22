package com.bilgeadam.SpringBootRestHibernateJPA.model;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Check;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Check(constraints = "NOTE < 101")
public class DersOgrenci {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long ID;

	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "OGRENCI_FK"))
	private Ogrenci ogrenci;

	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "DERS_FK"))
	private Ders ders;

	private int DEVAMSIZLIK;

	private long NOTE;

//	@Override
//	public String toString()
//	{
//		return "Ders_Ogrenci [ID=" + ID + ", ogrenci=" + ogrenci + ", ders=" + ders + ", DEVAMSIZLIK=" + DEVAMSIZLIK + ", NOT=" + NOTE + "]";
//	}

	public DersOgrenci(Ogrenci ogrenci, Ders ders, int dEVAMSIZLIK, long nOT) {
		this.ogrenci = ogrenci;
		this.ders = ders;
		DEVAMSIZLIK = dEVAMSIZLIK;
		NOTE = nOT;
	}
}
