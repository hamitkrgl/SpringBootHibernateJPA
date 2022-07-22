package com.bilgeadam.SpringBootRestHibernateJPA.endpoints;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bilgeadam.SpringBootRestHibernateJPA.model.Konu;
import com.bilgeadam.SpringBootRestHibernateJPA.service.KonuService;

@RestController
@RequestMapping(value = "konu")
public class KonuResource {
	@Autowired
	public KonuService konuService;

	@GetMapping(path = "getAll", produces = MediaType.APPLICATION_JSON_VALUE)
	public ArrayList<Konu> getAll() {
		// localhost:8080/konu/getAll
		ArrayList<Konu> liste = (ArrayList<Konu>) konuService.getAll();
		return liste;
	}

	// localhost:8080/konu/getById/1
	@GetMapping(path = "getById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Konu> getByIdEntity(@PathVariable Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(konuService.getById(id));
	}

	// localhost:8080/konu/findByName/java
	@GetMapping(path = "findByName/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Konu> findByName(@PathVariable String name) {
		return ResponseEntity.status(HttpStatus.OK).body(konuService.find(name));
	}

	// localhost:8080/konu/searchByName?exp=va
	@GetMapping(path = "searchByName", produces = MediaType.APPLICATION_JSON_VALUE)
	public ArrayList<Konu> searchByName(@RequestParam(name = "exp") String exp) {
		ArrayList<Konu> liste = (ArrayList<Konu>) konuService.findNameLike(exp);
		return liste;
	}

	// localhost:8080/konu/save
	@PostMapping(path = "save", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> save(@RequestBody Konu konu) {
		Konu result = konuService.save(konu);
		if (result != null) {
			return ResponseEntity.status(HttpStatus.CREATED).body(result.getNAME() + " isimli konu başarıyla eklendi");
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("konu bulunamadı!");
		}
	}

	@DeleteMapping(path = "delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> delete(@PathVariable Long id) {
		// localhost:8080/konu/delete/1
		if (konuService.delete(id)) {
			return ResponseEntity.status(HttpStatus.OK).body("Konu silindi");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(id + " id 'li konu bulunamadı");
		}

	}
}
