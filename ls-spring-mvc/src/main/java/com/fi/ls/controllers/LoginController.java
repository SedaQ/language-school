package com.fi.ls.controllers;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.fi.ls.dto.user.LSUserAuthenticateDTO;
import com.fi.ls.dto.user.LSUserCreateDTO;
import com.fi.ls.dto.user.LSUserDTO;
import com.fi.ls.facade.LSUserFacade;

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
		// register user
		LSUserCreateDTO user1 = new LSUserCreateDTO();
		user1.setEmail("pavelseda@email.cz");
		user1.setPassword("PA165PasswordTest");
		try {
			userFacade.registerUser(user1, user1.getPassword());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/login")
	public String login(@RequestParam(value="form-username") String email, @RequestParam(value="form-password") String password) {
		try {
			LSUserDTO userDTO = new LSUserDTO();
			userDTO.setId(userFacade.getUserByEmail(email).get().getId());
			userDTO.setEmail(email);
			userDTO.setPasswordHash(password);

			boolean isUserValid = userFacade.authenticate(userDTO);
			if (isUserValid) {
				System.out.println("GOOOOOD LOGIN PARAMETERS");
				return "home";
			} else {
				System.out.println("Bad Login parameters:!!!");
				return "badlogin";
				// model = new ModelAndView("bad login");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "badlogin";
	}

}
