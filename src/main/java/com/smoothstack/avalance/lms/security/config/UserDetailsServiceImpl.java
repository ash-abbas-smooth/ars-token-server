package com.smoothstack.avalance.lms.security.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service   // It has to be annotated with @Service.
public class UserDetailsServiceImpl implements UserDetailsService  {
	
	@Autowired
	private BCryptPasswordEncoder encoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		//TODO: USE DATABASE
		// hard coding the users. All passwords must be encoded.
		final List<AppUser> users = Arrays.asList(
			new AppUser(1, "vskitteral0@shinystat.com", encoder.encode("jtRQ3nKc84f"), "COUNTER"),
			new AppUser(9, "shewins8@linkedin.com", encoder.encode("mI9Pw8mHTIH9"), "TRAVELER"),
			new AppUser(8, "vstowell7@google.co.jp", encoder.encode("QlrEaZ"), "TRAVEL_AGENT")
		);
		
		
		//Gather all users 
		for(AppUser appUser: users) {
			if(appUser.getUsername().equals(username)) {
				
				// Remember that Spring needs roles to be in this format: "ROLE_" + userRole (i.e. "ROLE_ADMIN")
				// So, we need to set it to that format, so we can verify and compare roles (i.e. hasRole("ADMIN")).
				List<GrantedAuthority> grantedAuthorities = AuthorityUtils
		                	.commaSeparatedStringToAuthorityList("ROLE_" + appUser.getRole());
				
				// The "User" class is provided by Spring and represents a model class for user to be returned by UserDetailsService
				// And used by auth manager to verify and check user authentication.
				return new User(appUser.getUsername(), appUser.getPassword(), grantedAuthorities);
			}
		}
		
		// If user not found. Throw this exception.
		throw new UsernameNotFoundException("Username: " + username + " not found");
	}
	
	//temporary class
	private static class AppUser {
		private Integer id;
	    	private String username, password;
	    	private String role;
	    
		public AppUser(Integer id, String username, String password, String role) {
	    		this.id = id;
	    		this.username = username;
	    		this.password = password;
	    		this.role = role;
	    	}

		public Integer getId() {
			return id;
		}

		public String getUsername() {
			return username;
		}

		public String getPassword() {
			return password;
		}

		public String getRole() {
			return role;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public void setRole(String role) {
			this.role = role;
		}

		
	}
}