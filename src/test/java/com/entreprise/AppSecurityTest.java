package com.entreprise;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.cookie;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.Filter;
import javax.servlet.http.Cookie;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.security.crypto.codec.Hex;
import org.springframework.security.web.authentication.rememberme.InvalidCookieException;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.StringUtils;
import org.springframework.web.context.WebApplicationContext;

import com.entreprise.business.persistence.entities.User;
import com.entreprise.business.persistence.repositories.UserRepository;
import com.entreprise.business.service.impl.UserDetailsServiceImpl;
import com.entreprise.config.AbstractUnitTest;

/**
 * @author Sidi Amine
 *
 */
public class AppSecurityTest extends AbstractUnitTest {

    private static final String LOGIN_CORRECT = "user1";
    
    private static final String LOGIN_NOT_CORRECT = "XXXXXXXX";

    private static final String PASS_CORRECT = "test";
    
    private static final String BASE_URL = "http://localhost";

    private static final int TOKENS_LENGTH_EXPECTED = 3;

    private static final String PASS_BCRYPT_CORRECT = "$2a$04$jH1WY7nMxcZmZ6agYHGYFuY8Kw9KOuCJPLEmJHJeE5/f7lKOKa5y.";

    private static final String TOKEN_OK = "dXNlcjE6MTQxNjI3MzY5OTc5OToyMjJlMzI1MWFlM2RjNDMwYWVjZGZkZDBiNzA2OGI0Nw";
    
    private static final String DELIMITER = ":";
    
    private MockMvc mockMvc;
    
    @Autowired
    private WebApplicationContext context;

    @Autowired
    private Filter springSecurityFilterChain;
    
    @Mock
	private UserRepository userRepository;
    
    @Autowired
    private UserDetailsService userDetailsServiceImpl;

    @Value("${app.security.token.secret}")
    private String tokenKey;
    
    @Value("${app.security.token.cookieName}")
    private String cookieName;
    
    @Value("${app.security.token.tokenValiditySeconds}")
    private long tokenValiditySeconds;
    
    @Value("${app.security.token.defaultTokenValiditySeconds}")
    private long defaultTokenValiditySeconds;

    /**
     * setUp.
     * @throws Exception 
     */
    @Before
    public void setup() throws Exception {
	// Process mock annotations
	MockitoAnnotations.initMocks(this);
	
	this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).addFilters(this.springSecurityFilterChain)
		.build();
		
	// mock userRepository result
	User user = new User();
	user.setUsername(LOGIN_CORRECT);
	user.setPassword(PASS_BCRYPT_CORRECT);
	when(userRepository.findByUsername(eq(LOGIN_CORRECT))).thenReturn(user);
	when(userRepository.findByUsername(eq(LOGIN_NOT_CORRECT)))
	.thenThrow(new UsernameNotFoundException("No user found with username: " + LOGIN_NOT_CORRECT));
	
	ReflectionTestUtils.setField(this.<UserDetailsServiceImpl>getTargetObject(userDetailsServiceImpl), "userRepository", userRepository);
    }

    /**
     * @throws Exception
     *             exception
     */
    @Test
    public void loginFormSuccessWithRememberMeTest() throws Exception {
	// @formatter:off 
	MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders.post("/login")
		.param("username", LOGIN_CORRECT)
		.param("password", PASS_CORRECT)
		.param("_spring_security_remember_me", "1"))
		.andExpect(status().is3xxRedirection())
		.andExpect(redirectedUrl("/"))
		.andExpect(MockMvcResultMatchers.cookie().exists(this.cookieName))
		//.andDo(print())
		.andReturn();
	// @formatter:on
	String token = result.getResponse().getCookie(this.cookieName).getValue();
	assertNotNull("token must be not null.", token);
	String[] tokens = this.decodeCookie(token);
	assertNotNull("decoded token", tokens);
	assertEquals("decoded token length validation.", TOKENS_LENGTH_EXPECTED, tokens.length);
	assertEquals("Login validation in token.", LOGIN_CORRECT, tokens[0]);
	long actualTokenValiditySeconds = (new Long(tokens[1]).longValue() - System.currentTimeMillis()) / 1000;
	assertTrue("token expired.", actualTokenValiditySeconds > 0);
	assertTrue("token validity must be less than " + tokenValiditySeconds + " sec.", actualTokenValiditySeconds <= tokenValiditySeconds);
	// @formatter:off
	assertEquals("Token signature validation.", 
		this.makeTokenSignature(new Long(tokens[1]), LOGIN_CORRECT, PASS_BCRYPT_CORRECT), 
		tokens[2]);
	// @formatter:on
    }

    /**
     * @throws Exception
     *             exception
     */
    @Test
    public void loginFormSuccessWithoutRememberMeTest() throws Exception {
	// @formatter:off 
	MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders.post("/login")
		.param("username", LOGIN_CORRECT)
		.param("password", PASS_CORRECT))
		.andExpect(status().is3xxRedirection())
		.andExpect(redirectedUrl("/"))
		.andExpect(MockMvcResultMatchers.cookie().exists(this.cookieName))
		//.andDo(print())
		.andReturn();
	// @formatter:on
	String token = result.getResponse().getCookie(this.cookieName).getValue();
	assertNotNull("token must be not null.", token);
	String[] tokens = this.decodeCookie(token);
	assertNotNull("decoded token", tokens);
	assertEquals("decoded token length validation.", TOKENS_LENGTH_EXPECTED, tokens.length);
	assertEquals("Login validation in token.", LOGIN_CORRECT, tokens[0]);
	long actualTokenValiditySeconds = (new Long(tokens[1]).longValue() - System.currentTimeMillis()) / 1000;
	assertTrue("token validity must be less than " + tokenValiditySeconds + " sec.", actualTokenValiditySeconds <= defaultTokenValiditySeconds);
	assertTrue("token expiration.", actualTokenValiditySeconds > 0);
	// @formatter:off
	assertEquals("Token signature validation.", 
		this.makeTokenSignature(new Long(tokens[1]), LOGIN_CORRECT, PASS_BCRYPT_CORRECT), 
		tokens[2]);
	// @formatter:on
    }

    /**
     * @throws Exception
     *             exception
     */
    @Test
    public void loginFormFail1Test() throws Exception {
	// @formatter:off
	this.mockMvc.perform(MockMvcRequestBuilders.post("/login")
		.param("username", LOGIN_CORRECT)
		.param("password", "XXXXXXXXXXXX"))
		.andExpect(status().is3xxRedirection())
		.andExpect(cookie().value(this.cookieName, (String) null))
		.andExpect(redirectedUrl("/login?error"));
		//.andDo(print());
	// @formatter:on
    }
    
    /**
     * @throws Exception
     *             exception
     */
    @Test
    public void loginFormFail2Test() throws Exception {
	// @formatter:off
    	this.mockMvc.perform(MockMvcRequestBuilders.post("/login")
		.param("username", LOGIN_NOT_CORRECT)
		.param("password", "fdc"))
		.andExpect(status().is3xxRedirection())
		.andExpect(cookie().value(this.cookieName, (String) null))
		.andExpect(redirectedUrl("/login?error"));
    	//.andDo(print());
	// @formatter:on
    }

    /**
     * @throws Exception
     *             exception
     */
    @Test
    public void homeAccessWithoutTokenFailTest() throws Exception {
	// @formatter:off
	this.mockMvc.perform(MockMvcRequestBuilders.get("/"))
	.andExpect(status().is3xxRedirection())
	.andExpect(cookie().doesNotExist(this.cookieName))
	.andExpect(redirectedUrl(BASE_URL + "/login"));
	//.andDo(print());
	// @formatter:on
    }

    /**
     * @throws Exception
     *             exception
     */
    @Test
    public void homeAccessSuccessTest() throws Exception {
	// @formatter:off
	this.mockMvc.perform(MockMvcRequestBuilders.get("/")
		.cookie(new Cookie(cookieName, TOKEN_OK)))
		.andExpect(status().isOk());
		//.andDo(print());
	// @formatter:on
	//
    }

    private String makeTokenSignature(final long tokenExpiryTime, final String username, final String password) {
	String data = username + ":" + tokenExpiryTime + ":" + password + ":" + this.tokenKey;
	MessageDigest digest;
	try {
	    digest = MessageDigest.getInstance("MD5");
	} catch (NoSuchAlgorithmException e) {
	    throw new IllegalStateException("No MD5 algorithm available!");
	}

	return new String(Hex.encode(digest.digest(data.getBytes())));
    }

    private String[] decodeCookie(final String cookieValueIn) {
	String cookieValue = cookieValueIn;
	final int lengthMod = 4;
	for (int j = 0; j < cookieValue.length() % lengthMod; j++) {
	    cookieValue = cookieValue + "=";
	}

	if (!Base64.isBase64(cookieValue.getBytes())) {
	    throw new InvalidCookieException("Cookie token was not Base64 encoded; value was '" + cookieValue + "'");
	}

	String cookieAsPlainText = new String(Base64.decode(cookieValue.getBytes()));

	String[] tokens = StringUtils.delimitedListToStringArray(cookieAsPlainText, DELIMITER);

	if ((tokens[0].equalsIgnoreCase("http") || tokens[0].equalsIgnoreCase("https")) && tokens[1].startsWith("//")) {
	    // Assume we've accidentally split a URL (OpenID identifier)
	    String[] newTokens = new String[tokens.length - 1];
	    newTokens[0] = tokens[0] + ":" + tokens[1];
	    System.arraycopy(tokens, 2, newTokens, 1, newTokens.length - 1);
	    tokens = newTokens;
	}

	return tokens;
    }
}
