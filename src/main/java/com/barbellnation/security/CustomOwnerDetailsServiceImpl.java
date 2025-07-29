package com.barbellnation.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.barbellnation.dao.OwnerDao;

@Service
@Transactional
public class CustomOwnerDetailsServiceImpl implements UserDetailsService {
	private final OwnerDao ownerDao;
	
	public CustomOwnerDetailsServiceImpl(OwnerDao ownerDao) {
		super();
		this.ownerDao = ownerDao;
	}

	@Override
	public UserDetails loadUserByUsername(String email)
			throws UsernameNotFoundException {
		// invoke dao's method n return user details or throw the exc
		return ownerDao.findByEmail(email)
				.orElseThrow(() -> 
				new UsernameNotFoundException("Email not found!!!!"));
	}
}
