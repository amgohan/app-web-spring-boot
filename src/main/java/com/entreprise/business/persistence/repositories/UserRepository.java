package com.entreprise.business.persistence.repositories;

import javax.transaction.Transactional;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.entreprise.business.persistence.entities.User;

/**
 * User repository.
 * @author Sidi Amine
 *
 */
@Transactional
public interface UserRepository extends PagingAndSortingRepository<User, Long> {
	
	/** find User by username.
	 * @param username the given username
	 * @return User
	 */
	User findByUsername(String username);

}
