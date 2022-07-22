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

import com.bilgeadam.SpringBootRestHibernateJPA.model.Ders;
import com.bilgeadam.SpringBootRestHibernateJPA.service.DersService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(value = "ders")
@Tag(description = "Ders endpointleri", name = "Ders")
public class DersResource {
	@Autowired
	public DersService dersService;

	@GetMapping(path = "getAll", produces = MediaType.APPLICATION_JSON_VALUE)
	public ArrayList<Ders> getAll() {
		// localhost:8080/ders/getAll
		ArrayList<Ders> liste = (ArrayList<Ders>) dersService.getAll();
		return liste;
	}

	// localhost:8080/ders/getById/1
	@GetMapping(path = "getById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Ders> getByIdEntity(@PathVariable Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(dersService.getById(id));
	}

	// localhost:8080/ders/save
	@PostMapping(path = "save", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> save(@RequestBody Ders ders) {
		Ders result = dersService.save(ders);
		if (result != null) {
			return ResponseEntity.status(HttpStatus.CREATED).body(result.getID() + " li ders başarıyla eklendi");
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("ders bulunamadı!");
		}
	}

	@DeleteMapping(path = "delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> delete(@PathVariable Long id) {
		// localhost:8080/ders/delete/1
		if (dersService.delete(id)) {
			return ResponseEntity.status(HttpStatus.OK).body("ders silindi");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(id + " id 'li ders bulunamadı");
		}

	}
}
