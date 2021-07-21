package io.sunuh.springboot;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import io.sunuh.springboot.entity.DAOAuthority;
import io.sunuh.springboot.entity.DAOUser;
import io.sunuh.springboot.repository.UserRepository;

@SpringBootApplication
public class SpringbootBackendApplication {

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository userRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringbootBackendApplication.class, args);
	}
	
	
//	@PostConstruct
//	protected void init() {
//		List<DAOAuthority> authorityList = new ArrayList<>();
//		
//		authorityList.add(createAuthority("USER","User role"));
//		authorityList.add(createAuthority("ADMIN","Admin role"));
//		
//		DAOUser daoUser = new DAOUser();
//		
//		daoUser.setUsername("aris");
//		daoUser.setFirstName("Aris Affandy");
//		daoUser.setLastName("Sunuh");
//		daoUser.setPassword(passwordEncoder.encode("password123456"));
//		daoUser.setEnabled(true);
//		daoUser.setAuthorities(authorityList);
//		
//		userRepository.save(daoUser);
//	}
//	
//	private DAOAuthority createAuthority(String roleCode, String roleDescription) {
//		DAOAuthority daoAuthority = new DAOAuthority();
//		daoAuthority.setRoleCode(roleCode);
//		daoAuthority.setRoleDescription(roleDescription);
//		return daoAuthority;
//	}

}
