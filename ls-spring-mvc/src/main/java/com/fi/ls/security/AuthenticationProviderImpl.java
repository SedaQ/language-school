package com.fi.ls.security;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Component;

import com.fi.ls.dto.user.LSUserDTO;

import com.fi.ls.facade.LSUserFacade;

/**
 * @author Pavel Šeda (441048)
 *
 */
@Component
public class AuthenticationProviderImpl implements AuthenticationProvider {

	@Inject
	private LSUserFacade userFacade;

	@Override
	public Authentication authenticate(Authentication auth) throws AuthenticationException {
		String email = auth.getName();

		LSUserDTO user = userFacade.getUserByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException("Provide valid email: " + email));

		String pwd = (String) auth.getCredentials();

		LSUserDTO userForAuth = new LSUserDTO();
		userForAuth.setId(user.getId());
		userForAuth.setEmail(email);
		userForAuth.setPasswordHash(pwd);

		if (!userFacade.authenticate(userForAuth)) {
			throw new BadCredentialsException("Provide valid email or password");
		}

		
		List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(user.getUserRole());
		return new UsernamePasswordAuthenticationToken(email, pwd, authorities);
	}
	
	@Override
	public boolean supports(Class<?> auth) {
		return true;
	}

}
