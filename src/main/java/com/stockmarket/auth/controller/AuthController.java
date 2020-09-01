package com.stockmarket.auth.controller;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.stockmarket.auth.entity.User;
import com.stockmarket.auth.models.AuthenticateRequest;
import com.stockmarket.auth.models.JwtAuthenticationResponse;
import com.stockmarket.auth.models.UserPrincipal;
import com.stockmarket.auth.repository.UserRepository;
import com.stockmarket.auth.service.EmailService;
import com.stockmarket.auth.service.JWTTokenProvider;

@RestController
@RequestMapping("/authenticate")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {
	@Autowired
	UserRepository userRepository;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JWTTokenProvider jwtTokenProvider;
	@Autowired
	EmailService emailService;

	@PostMapping
	public ResponseEntity<Object> authenticateUser(@RequestBody AuthenticateRequest authenticateRequest) {
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
				authenticateRequest.getUserName(), authenticateRequest.getPassword()));
		String token = jwtTokenProvider.generateToken((UserPrincipal) authentication.getPrincipal());

		return ResponseEntity.ok(new JwtAuthenticationResponse(token));
	}

	@PostMapping("/signup")
	@Transactional(rollbackFor = MailException.class)
	public ResponseEntity<Object> signup_user(@RequestBody User user) {

		try {
			user.setConfirmed(false);
			user.setUserType("ROLE_USER");
			String code = UUID.randomUUID().toString().split("-")[0];
			user.setVerification_code(code);
			userRepository.save(user);
			emailService.sendSimpleMessage(user.getEmail(), "Verfication Code", "Your verification code is " + code);
			return ResponseEntity.ok(null);
		} catch (DataIntegrityViolationException e) {
			return new ResponseEntity<>("Username Exists", HttpStatus.BAD_REQUEST);
		}

	}

	@PostMapping("/signup/validate")
	public ResponseEntity<Object> validateUser(@RequestParam(name = "username") String username,
			@RequestParam(name = "code") String code) {

		User user = userRepository.findByUsername(username);
		if (user == null) {
			return new ResponseEntity<>("User Doesn't exist", HttpStatus.BAD_REQUEST);
		}
		if (user.getConfirmed() == true) {
			return new ResponseEntity<>("User already verified", HttpStatus.BAD_REQUEST);
		}
		if (code.equals(user.getVerification_code())) {
			user.setConfirmed(true);
			user.setVerification_code("N/A");
			userRepository.save(user);
			return ResponseEntity.ok(null);
		} else {
			return new ResponseEntity<>("Incorrect Code", HttpStatus.BAD_REQUEST);
		}

	}

	@GetMapping("/logout")
	public ResponseEntity<Object> logoutUser(HttpServletRequest request, HttpServletResponse response) {

		return ResponseEntity.ok(null);
	}
}
