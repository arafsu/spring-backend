package io.sunuh.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import io.sunuh.springboot.entity.DAOUser;
import io.sunuh.springboot.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;
//
//	@Autowired
//	private PasswordEncoder bcryptEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		DAOUser user = userRepository.findByUsername(username);
		
//		if("admin".equals(userName)) {
//			//password: "password"
//			return new User("admin","$2y$12$fqp3HbZvKe/9DjLTGAUAr.UlUTxp9gIht35JNaIqt5KVs84t5odL.", new ArrayList<>());
//		}else {
//			throw new UsernameNotFoundException("User not found with username: " + userName);
//		}		
		
//		   "username": "aris",
//		    "password": "password123456"
		
		if (user == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		return user;
	}
	
//	public DAOUser save(UserDTO user) {
//		DAOUser newUser = new DAOUser();
//		newUser.setUsername(user.getUsername());
//		newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
//		return userRepository.save(newUser);
//	}	
}
