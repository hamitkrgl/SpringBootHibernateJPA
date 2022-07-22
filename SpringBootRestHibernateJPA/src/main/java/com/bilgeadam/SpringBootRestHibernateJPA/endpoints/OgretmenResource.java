package com.bilgeadam.SpringBootRestHibernateJPA.endpoints;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
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

import com.bilgeadam.SpringBootRestHibernateJPA.model.Ders;
import com.bilgeadam.SpringBootRestHibernateJPA.model.Ogretmen;
import com.bilgeadam.SpringBootRestHibernateJPA.service.OgretmenService;

@RestController
@RequestMapping(value = "ogretmen")
public class OgretmenResource {
	@Autowired
	public OgretmenService ogretmenService;

//	@Value(value = "#ogretmen.save.info")
//	public String info;

	@Autowired
	public MessageSource messageSource;

	// produces yazmazsam 406 hatası alabiliyorum
	@GetMapping(path = "getAll", produces = MediaType.APPLICATION_JSON_VALUE)
	public ArrayList<Ogretmen> getAll() {
		// localhost:8080/ogretmen/getAll
		ArrayList<Ogretmen> liste = (ArrayList<Ogretmen>) ogretmenService.getAll();
		return liste;
	}

	// localhost:8080/ogretmen/getById/1
	@GetMapping(path = "getById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Ogretmen> getByIdEntity(@PathVariable Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(ogretmenService.getById(id));
	}

	// localhost:8080/ogretmen/findByName/numan
	@GetMapping(path = "findByName/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Ogretmen> findByName(@PathVariable String name) {
		return ResponseEntity.status(HttpStatus.OK).body(ogretmenService.find(name));
	}

	// localhost:8080/ogretmen/searchByName?exp=man
	@GetMapping(path = "searchByName", produces = MediaType.APPLICATION_JSON_VALUE)
	public ArrayList<Ogretmen> searchByName(@RequestParam(name = "exp") String exp) {
		ArrayList<Ogretmen> liste = (ArrayList<Ogretmen>) ogretmenService.findNameLike(exp);
		return liste;
	}

	// localhost:8080/ogretmen/getById/1/dersler
	@GetMapping(path = "getById/{id}/dersler", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Set<Ders>> getByIdDersler(@PathVariable Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(ogretmenService.getById(id).getDersler());
	}

	// localhost:8080/ogretmen/save
	@PostMapping(path = "save", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> save(@RequestBody Ogretmen ogretmen, Locale locale) {
		Ogretmen result = ogretmenService.save(ogretmen);
		if (result != null) {
			System.err.println(locale.toString());
			String info = messageSource.getMessage("ogretmen.save.info", null, locale);
			return ResponseEntity.status(HttpStatus.CREATED).body(result.getNAME() + " " + info);
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("öğretmen kaydedilirken hata oluştu!");
		}
	}

	@DeleteMapping(path = "delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> delete(@PathVariable Long id) {
		// localhost:8080/ogretmen/delete/1
		if (ogretmenService.delete(id)) {
			return ResponseEntity.status(HttpStatus.OK).body("Öğretmen silindi");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(id + " id 'li öğretmen bulunamadı");
		}

	}

}
