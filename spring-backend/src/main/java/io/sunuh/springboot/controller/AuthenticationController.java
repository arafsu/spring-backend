package io.sunuh.springboot.controller;

import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.security.spec.InvalidKeySpecException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.sunuh.springboot.entity.DAOUser;
import io.sunuh.springboot.model.JwtRequest;
import io.sunuh.springboot.model.JwtResponse;
import io.sunuh.springboot.model.UserInfo;
import io.sunuh.springboot.utility.JWTUtility;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/")
public class AuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JWTUtility jwtUtility;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@PostMapping("/auth/login")
	public ResponseEntity<?> login(@RequestBody JwtRequest jwtRequest) 
			throws InvalidKeySpecException, NoSuchAlgorithmException  {
		
		final Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						jwtRequest.getUserName(),
						jwtRequest.getPassword()
						));
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		DAOUser daoUser = (DAOUser) authentication.getPrincipal();
		String token = jwtUtility.generateToken(daoUser.getUsername());
		
		JwtResponse jwtResponse = new JwtResponse();
		jwtResponse.setJwtToken(token);
		
		return ResponseEntity.ok(jwtResponse);
	}
	
	@GetMapping("/auth/userinfo")
	public ResponseEntity<?> getUserInfo(Principal user) {
		DAOUser userObjDaoUser = (DAOUser) userDetailsService.loadUserByUsername(user.getName());
		
		UserInfo userInfo = new UserInfo();
		
		userInfo.setFirstName(userObjDaoUser.getFirstName());
		userInfo.setLastName(userObjDaoUser.getLastName());
		userInfo.setRoles(userObjDaoUser.getAuthorities().toArray());
		
		return ResponseEntity.ok(userInfo);
	}
}
