package com.fi.ls.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.fi.ls.dto.user.LSUserDTO;
import com.fi.ls.facade.LSUserFacade;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;
import java.io.IOException;

@WebFilter(urlPatterns = { "/lsuser/*","/language-school/*" })
public class ProtectFilter implements Filter {

	final static Logger log = LoggerFactory.getLogger(ProtectFilter.class);

	@Override
	public void doFilter(ServletRequest r, ServletResponse s, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) r;
		HttpServletResponse response = (HttpServletResponse) s;

		String auth = request.getHeader("Authorization");
		if (auth == null) {
			response401(response);
			return;
		}
		String[] creds = parseAuthHeader(auth);
		String logname = creds[0];
		String password = creds[1];

		// get Spring context and UserFacade from it
		LSUserFacade userFacade = WebApplicationContextUtils.getWebApplicationContext(r.getServletContext())
				.getBean(LSUserFacade.class);
		LSUserDTO matchingUser = userFacade.getUserByEmail(logname).get();
		if (matchingUser == null) {
			log.warn("no user with email {}", logname);
			response401(response);
			return;
		}
		LSUserDTO userAuthenticateDTO = new LSUserDTO();
		userAuthenticateDTO.setId(matchingUser.getId());
		userAuthenticateDTO.setPasswordHash(password);
		// if (!userFacade.isAdmin(matchingUser)) {
		// log.warn("user not admin {}", matchingUser);
		// response401(response);
		// return;
		// }
		if (!userFacade.authenticate(userAuthenticateDTO)) {
			log.warn("wrong credentials: user={} password={}", creds[0], creds[1]);
			response401(response);
			return;
		}
		request.setAttribute("authenticatedUser", matchingUser);
		chain.doFilter(request, response);
	}

	private String[] parseAuthHeader(String auth) {
		return new String(DatatypeConverter.parseBase64Binary(auth.split(" ")[1])).split(":", 2);
	}

	private void response401(HttpServletResponse response) throws IOException {
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		response.setHeader("WWW-Authenticate", "Basic realm=\"type email and password\"");
		response.getWriter()
				.println("<html><body><h1>401 Unauthorized</h1> First of all you have to login!</body></html>");
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void destroy() {
	}
}
