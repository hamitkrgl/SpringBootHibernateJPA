package com.bilgeadam.SpringBootRestHibernateJPA;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.bilgeadam.SpringBootRestHibernateJPA.model.Ogrenci;
import com.fasterxml.jackson.databind.ObjectMapper;

//test paketine yazdığım için mavenla otomatik entegre oldu.
//order ile testlerin çalışma sırasını belirtebiliriz.
@SpringBootTest
@DisplayName(value = "Spring Boot Test")
@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
@AutoConfigureMockMvc
class DemoApplicationTests {

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	@Autowired
	public MockMvc mock;

	@Test
	@Order(value = 1)
	public void saveOgrenci() throws Exception {
		String ogrenci = new ObjectMapper().writeValueAsString(new Ogrenci("muhammed", 490, 4));
		RequestBuilder request = MockMvcRequestBuilders.post("/ogrenci/save").content(ogrenci)
				.contentType(MediaType.APPLICATION_JSON);
		String result = mock.perform(request).andReturn().getResponse().getContentAsString();
		Assertions.assertEquals("muhammed isimli ogrenci başarıyla eklendi", result);
		System.err.println("------------->" + result);
	}

	@Test
	@Order(value = 2)
	public void getOgrenciById() throws Exception {
		// "id": 2,"name": "muhammed","ogr_NUMBER": 490,"year": 4
		// "id": 1,"name": "hamit","ogr_NUMBER": 125,"year": 4
		RequestBuilder request = MockMvcRequestBuilders.get("/ogrenci/getById/2");
		// assertion gibi expectation yazabilirim.
		ResultMatcher numberMatcher = MockMvcResultMatchers.jsonPath("$.ogr_NUMBER").value("490");
		ResultMatcher nameMatcher = MockMvcResultMatchers.jsonPath("$.name").value("muhammed");
		ResultMatcher ıdMatcher = MockMvcResultMatchers.jsonPath("$.id").value("2");
		ResultMatcher yearMatcher = MockMvcResultMatchers.jsonPath("$.year").value("4");
		ResultMatcher okMatcher = MockMvcResultMatchers.status().isOk();
		mock.perform(request).andExpect(numberMatcher).andExpect(nameMatcher).andExpect(ıdMatcher)
				.andExpect(yearMatcher).andExpect(okMatcher);
	}

	@Test
	@Order(value = 3)
	public void getOgrenciByName() throws Exception {
		// "id": 2,"name": "muhammed","ogr_NUMBER": 490,"year": 4
		// "id": 1,"name": "hamit","ogr_NUMBER": 125,"year": 4
		RequestBuilder request = MockMvcRequestBuilders.get("/ogrenci/findByName/muhammed");
		// assertion gibi expectation yazabilirim.
		ResultMatcher numberMatcher = MockMvcResultMatchers.jsonPath("$.ogr_NUMBER").value("490");
		ResultMatcher nameMatcher = MockMvcResultMatchers.jsonPath("$.name").value("muhammed");
		ResultMatcher ıdMatcher = MockMvcResultMatchers.jsonPath("$.id").value("2");
		ResultMatcher yearMatcher = MockMvcResultMatchers.jsonPath("$.year").value("4");
		ResultMatcher okMatcher = MockMvcResultMatchers.status().isOk();
		mock.perform(request).andExpect(numberMatcher).andExpect(nameMatcher).andExpect(ıdMatcher)
				.andExpect(yearMatcher).andExpect(okMatcher);
	}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/*
	 * junit 4 de böyle testler yazılabilir.
	 * 
	 * @Autowired public TestRestTemplate restTemplate;
	 * 
	 * @BeforeEach public void setUp() { this.restTemplate = new TestRestTemplate();
	 * }
	 * 
	 * @Test public void testGet() throws Exception { String
	 * body=restTemplate.getForEntity("localhost:8080/ogrenci/getAll",
	 * String.class).getBody(); Ogrenci ogrenci=new ObjectMapper().readValue(body,
	 * Ogrenci.class); Assert.assertEquals(new Ogrenci()) }
	 * 
	 * @Test public void testSave() { Ogrenci ogrenci = new Ogrenci("muhammed", 123,
	 * 4); ResponseEntity<String> result =
	 * restTemplate.exchange("http://localhost:8080/ogrenci/save", HttpMethod.POST,
	 * new HttpEntity<>(ogrenci), String.class);
	 * System.err.println(result.getStatusCode() + " - " + result.getBody()); }
	 * 
	 * @Test void contextLoads() {
	 * System.err.println("------------Testler çalışıyor---------------------"); }
	 */
}