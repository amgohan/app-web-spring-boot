package com.entreprise.business.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.entreprise.business.persistence.repositories.UserRepository;

/**
 * Custom implementation to support Spring Security login based on custom
 * authentication of persisted databases.
 * 
 * @author Sidi Amine
 * 
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
    	com.entreprise.business.persistence.entities.User user = userRepository.findByUsername(username);
		if (user == null) {
	        throw new UsernameNotFoundException("No user found with username: " + username);
	    }
		boolean enabled = true;
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		// @formatter:off
		UserDetails userDetails = new User(user.getUsername(), 
										   user.getPassword(), 
										   enabled, 
										   accountNonExpired, 
										   credentialsNonExpired,
										   accountNonLocked, 
										   authorities);
		// @formatter:on
		return userDetails;
    }

}
