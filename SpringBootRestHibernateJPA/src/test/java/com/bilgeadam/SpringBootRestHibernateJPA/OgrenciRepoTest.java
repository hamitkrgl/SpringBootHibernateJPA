package com.bilgeadam.SpringBootRestHibernateJPA;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.bilgeadam.SpringBootRestHibernateJPA.model.Ogrenci;
import com.bilgeadam.SpringBootRestHibernateJPA.repo.OgrenciRepo;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class OgrenciRepoTest {

	@Autowired
	public OgrenciRepo ogrenciRepo;

	@Test
	@Rollback(value = false)
	public void saveTest() {
		Ogrenci ogrenci = new Ogrenci("muhammed", 490, 2);
		ogrenciRepo.save(ogrenci);
		Ogrenci result = ogrenciRepo.findOgrenciByNAME("muhammed");
		Assertions.assertEquals(result.getOGRNUMBER(), ogrenci.getOGRNUMBER());
	}

}
