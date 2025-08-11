package com.barbellnation.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.barbellnation.dto.AuthRequest;
import com.barbellnation.dto.AuthResponse;
import com.barbellnation.dto.UserReqDTO;
import com.barbellnation.entities.Owner;
import com.barbellnation.security.JwtUtils;
import com.barbellnation.service.OwnerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:5173")
public class UserSignUpSignInController {
	private final OwnerService ownerService;
	private final AuthenticationManager authenticationManager;
	private final JwtUtils jwtUtils;
	
	public UserSignUpSignInController(OwnerService ownerService, AuthenticationManager authenticationManager,
			JwtUtils jwtUtils) {
		super();
		this.ownerService = ownerService;
		this.authenticationManager = authenticationManager;
		this.jwtUtils = jwtUtils;
	}
	
	@PostMapping("/signup")
	public ResponseEntity<?> signUpUser(@RequestBody @Valid UserReqDTO dto) {
		System.out.println("in sign up " + dto);
		return ResponseEntity.status(HttpStatus.CREATED).body(ownerService.signUp(dto));
	}
	
	/*
	 * User sign in URL - http://host:port/users/signin Method - POST Payload - JSON
	 * representation of auth req dto (email , pwd) Response -User resp dto | error
	 * - api resp with err mesg
	 * 
	 */
	@PostMapping("/signin")
	public ResponseEntity<?> userSignIn(@RequestBody @Valid AuthRequest dto) {
		System.out.println("in signin " + dto);
		// 1.create auth token
		UsernamePasswordAuthenticationToken authToken = 
				new UsernamePasswordAuthenticationToken(dto.getEmail(),
				dto.getPassword());
		try {
			// 2. invoke AuthMgr's authenticate
			Authentication retAuth = 
					authenticationManager.authenticate(authToken);
			
			Owner loggedInOwner = (Owner) retAuth.getPrincipal();
			Long ownerId = loggedInOwner.getId();

			System.out.println(retAuth.isAuthenticated());//true
			System.out.println(retAuth.getPrincipal());//user entity
			System.out.println(retAuth.getPrincipal().getClass());//
			System.out.println(retAuth.getAuthorities());//ROLE_ADMIN | ROLE_CUSTOMER
			//success
			return ResponseEntity.status
					(HttpStatus.CREATED) //JWT - created
					.body(
							new AuthResponse("Auth successful!!!"
									,jwtUtils.generateJwtToken(retAuth), ownerId));
		} catch (Exception e) {
			System.out.println(e);
			return ResponseEntity
					.status(HttpStatus.UNAUTHORIZED)
					.body(new com.barbellnation.dto.ApiResponse(e.getMessage()));
		}

	}
}
