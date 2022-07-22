package com.bilgeadam.SpringBootRestHibernateJPA;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.bilgeadam.SpringBootRestHibernateJPA.endpoints.OgrenciResource;
import com.bilgeadam.SpringBootRestHibernateJPA.model.Ogrenci;
import com.bilgeadam.SpringBootRestHibernateJPA.service.OgrenciService;

//junit4 kullanıyorsanız
//@RunWith(SpringRunner.class)
@WebMvcTest(controllers = OgrenciResource.class)
public class OgrenciResourceMVCTest {
	@Autowired
	public MockMvc mock;

	@MockBean
	public OgrenciService ogrenciService;

	@Test
	@Order(value = 3)
	public void getOgrenciByName() throws Exception {

		Ogrenci ogrenci = new Ogrenci("muhammed", 490, 4);
		Mockito.when(ogrenciService.findByName("muhammed")).thenReturn(ogrenci);

		// "name": "muhammed","ogr_NUMBER": 490,"year": 4
		RequestBuilder request = MockMvcRequestBuilders.get("/ogrenci/findByName/muhammed");
		// assertion gibi expectation yazabilirim.
		ResultMatcher numberMatcher = MockMvcResultMatchers.jsonPath("$.ogr_NUMBER").value("490");
		ResultMatcher nameMatcher = MockMvcResultMatchers.jsonPath("$.name").value("muhammed");
		ResultMatcher yearMatcher = MockMvcResultMatchers.jsonPath("$.year").value("4");
		ResultMatcher okMatcher = MockMvcResultMatchers.status().isOk();
		mock.perform(request).andExpect(numberMatcher).andExpect(nameMatcher).andExpect(yearMatcher)
				.andExpect(okMatcher);
	}

}
