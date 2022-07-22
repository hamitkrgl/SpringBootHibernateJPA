package com.bilgeadam.SpringBootRestHibernateJPA.service;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

import com.bilgeadam.SpringBootRestHibernateJPA.model.Ogretmen;
import com.bilgeadam.SpringBootRestHibernateJPA.repo.OgretmenRepo;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

// bu bir porxy katmanı
// bussiness logic için yazılır
@Service
@AllArgsConstructor
@Slf4j // (topic = "OGRETMENSERVICE_CLASS")
public class OgretmenService {
	private OgretmenRepo ogretmenRepo;

	public List<Ogretmen> getAll() {
		// trace-debug-info-warning-error-fatal
		log.debug("--------------------Ogretmen debug getAll çalıştı.-----------------------");
		log.info("--------------------Ogretmen info getAll çalıştı.-----------------------");
		log.error("--------------------Ogretmen error getAll çalıştı.-----------------------");

		return ogretmenRepo.findAll();
	}

	public Ogretmen getById(Long id) {
		return ogretmenRepo.findById(id).get();
	}

	public Ogretmen find(String isim) {
		return ogretmenRepo.findOgretmenByNAME(isim);
	}

	public List<Ogretmen> findNameLike(String expression) {
		return ogretmenRepo.findAllByNAMELike("%" + expression + "%", Sort.by(Order.desc("ID")));
//		return ogretmenRepo.findAllByNAMELikeOrderByNAMEDesc("%" + expression + "%");
		// return ogretmenRepo.findByOgretmenName("%" + expression + "%");
	}

	public Ogretmen save(Ogretmen ogretmen) {
		return ogretmenRepo.save(ogretmen);
	}

	public Ogretmen update(Ogretmen ogretmen) {
		return ogretmenRepo.save(ogretmen);
	}

	public boolean delete(Long id) {
		try {
			ogretmenRepo.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
