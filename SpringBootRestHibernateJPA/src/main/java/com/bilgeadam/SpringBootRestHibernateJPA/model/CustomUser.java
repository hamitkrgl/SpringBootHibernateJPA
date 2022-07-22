package com.bilgeadam.SpringBootRestHibernateJPA.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class CustomUser {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(length = 50, nullable = false, unique = true)
	private String username = "";
	@Column(length = 150, nullable = false)
	private String password = "";

	private boolean enabled = true;

//	public CustomUser(String username, String password) {
//		this.username = username;
//		this.password = password;
//	}
	@ManyToMany(fetch = FetchType.EAGER)
	private List<Role> roles;
}
