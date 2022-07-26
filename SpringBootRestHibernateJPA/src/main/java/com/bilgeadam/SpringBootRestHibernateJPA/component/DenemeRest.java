package com.bilgeadam.SpringBootRestHibernateJPA.component;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "deneme")
@RequiredArgsConstructor
//@Profile(value = "test")
public class DenemeRest
{
	@NonNull
	private ApplicationContext applicationContext;
	
	@GetMapping(path = "beans")
	public ResponseEntity<String> beans()
	{
		// localhost:8080/deneme/beans
		String[] names = applicationContext.getBeanDefinitionNames();
		Arrays.sort(names);
		StringBuilder builder = new StringBuilder();
		builder.append("---- " + names.length + " ----").append("<br>");
		for (String name : names)
		{
			builder.append(name + " ---> " + applicationContext.getBean(name).getClass().getName()).append("<br>");
		}
		return ResponseEntity.ok(builder.toString());
	}
}