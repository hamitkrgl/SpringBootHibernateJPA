package com.bilgeadam.SpringBootRestHibernateJPA.service;

import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bilgeadam.SpringBootRestHibernateJPA.model.CustomUser;
import com.bilgeadam.SpringBootRestHibernateJPA.repo.UserRepo;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

	private UserRepo userRepository;

	private PasswordEncoder passwordEncoder;

	public boolean save(CustomUser user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user) != null;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// kendi user 'ımdan sprin security user 'ına dönüştürüyorum
		CustomUser myUser = userRepository.findByusername(username);
		if (myUser == null) {
			throw new UsernameNotFoundException("Kullanıcı adı bulunamadı");
		}
		// CustomUser sınıfı spring user sınıfından extend edilebilir
		UserBuilder builder = org.springframework.security.core.userdetails.User.withUsername(myUser.getUsername());
		builder.password(myUser.getPassword());
		builder.authorities(myUser.getRoles());
		return builder.build();
	}

}