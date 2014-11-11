package com.entreprise.business.persistence.repositories;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.Date;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.entreprise.business.persistence.entities.User;
import com.entreprise.config.AbstractUnitTest;

/**
 * UserRepository tests.
 * @author Sidi Amine
 *
 */
public class UserRepositoryTest extends AbstractUnitTest {
	
	private static final User USER1;
	private static final String EMAIL_NOT_FOUND;
	
	@Autowired
	UserRepository userRepository;
	
	static {
		EMAIL_NOT_FOUND = "XXXX@XXXX.XXX";
		USER1 = new User();
		USER1.setFirstName("user1FirstName");
		USER1.setLastName("user1LastName");
		USER1.setUsername("user1Login");
		USER1.setPassword("user1Password");
		USER1.setEmail("user1@email.com");
		USER1.setEnabled(true);
		USER1.setLastModified(new Date());
	}
	
	/**
	 * setUp.
	 */
	@Before
	public void setUp() {
		userRepository.deleteAll();
		userRepository.save(USER1);
	}
	
	/**
	 * tearDown.
	 */
	@After
	public void tearDown() {
		userRepository.deleteAll();
	}
	
	/**
	 * find by email.
	 */
	@Test
	public void findByEmailTest() {
		User expectedUser = userRepository.findByUsername(USER1.getUsername());
		Date lastModified = expectedUser.getLastModified();
		assertThat(expectedUser, hasProperty("firstName", equalTo(USER1.getFirstName())));
		assertThat(expectedUser, hasProperty("lastName", equalTo(USER1.getLastName())));
		assertThat(expectedUser, hasProperty("email", equalTo(USER1.getEmail())));
		assertThat(expectedUser, hasProperty("username", equalTo(USER1.getUsername())));
		assertThat(expectedUser, hasProperty("password", equalTo(USER1.getPassword())));
		assertThat(expectedUser, hasProperty("lastModified", equalTo(lastModified)));
		//assertThat(expectedUser, samePropertyValuesAs(USER1));
	}
	
	/**
	 * find by email not found.
	 */
	@Test
	public void findByEmailNotFoundTest() {
		User expectedUser = userRepository.findByUsername(EMAIL_NOT_FOUND);
		Assert.assertNull(expectedUser);
	}

}
