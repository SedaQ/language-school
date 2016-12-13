package com.fi.ls.controllers;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fi.ls.dto.user.LSUserCreateDTO;
import com.fi.ls.dto.user.LSUserDTO;
import com.fi.ls.enums.UserRoles;
import com.fi.ls.facade.LSUserFacade;
import com.fi.ls.security.AuthenticationProviderImpl;

/**
 * @author Pavel Šeda (441048)
 *
 */
@Controller
public class LoginController {

	@Inject
	private LSUserFacade userFacade;

	@PostConstruct
	public void initUsers() {
		LSUserCreateDTO user1 = new LSUserCreateDTO();
		user1.setEmail("pavelseda@email.cz");
		user1.setPassword("test");
		user1.setUserRole(UserRoles.ROLE_ADMIN.name());

		try {
			userFacade.registerUser(user1, user1.getPassword());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/login")
	public String login(Model model) {
		return "login";
	}

    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){    
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }

}
