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
import org.springframework.web.bind.annotation.RestController;

import com.bilgeadam.SpringBootRestHibernateJPA.model.DersOgrenci;
import com.bilgeadam.SpringBootRestHibernateJPA.service.DersOgrenciService;

@RestController
@RequestMapping(value = "dersogrenci")
public class DersOgrenciResource {
	@Autowired
	public DersOgrenciService dersOgrenciService;

	@GetMapping(path = "getAll", produces = MediaType.APPLICATION_JSON_VALUE)
	public ArrayList<DersOgrenci> getAll() {
		// localhost:8080/dersogrenci/getAll
		ArrayList<DersOgrenci> liste = (ArrayList<DersOgrenci>) dersOgrenciService.getAll();
		return liste;
	}

	// localhost:8080/dersogrenci/getById/1
	@GetMapping(path = "getById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DersOgrenci> getByIdEntity(@PathVariable Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(dersOgrenciService.getById(id));
	}

	// localhost:8080/dersogrenci/save
	@PostMapping(path = "save", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> save(@RequestBody DersOgrenci dersOgrenci) {
		DersOgrenci result = dersOgrenciService.save(dersOgrenci);
		if (result != null) {
			return ResponseEntity.status(HttpStatus.CREATED).body(result.getID() + " Id'li veri başarıyla eklendi");
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("dersogrenci verisi bulunamadı!");
		}
	}

	@DeleteMapping(path = "delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> delete(@PathVariable Long id) {
		// localhost:8080/dersogrenci/delete/1
		if (dersOgrenciService.delete(id)) {
			return ResponseEntity.status(HttpStatus.OK).body("dersogrenci verisi silindi");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(id + " id 'li veri bulunamadı");
		}

	}
}
