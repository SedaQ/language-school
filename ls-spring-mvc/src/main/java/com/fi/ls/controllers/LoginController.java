package com.fi.ls.controllers;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fi.ls.dto.user.LSUserCreateDTO;
import com.fi.ls.dto.user.LSUserDTO;
import com.fi.ls.enums.UserRoles;
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
		LSUserCreateDTO user1 = new LSUserCreateDTO();
		user1.setEmail("pavelseda@email.cz");
		user1.setPassword("test");
		user1.setUserRole(UserRoles.USER_ADMIN.name());

		try {
			userFacade.registerUser(user1, user1.getPassword());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/language-school", method = RequestMethod.POST)
	public String login(@RequestParam(value = "form-username") String email,
			@RequestParam(value = "form-password") String password, Model model) {
		try {
			LSUserDTO userId = userFacade.getUserByEmail(email).get();

			LSUserDTO userDTO = new LSUserDTO();
			userDTO.setId(userId.getId());
			userDTO.setEmail(email);
			userDTO.setPasswordHash(password);
			userDTO.setUserRole(userId.getUserRole());

			boolean isUserValid = userFacade.authenticate(userDTO);
			if (isUserValid) {
				if (userFacade.isAdmin(userDTO)) {
					// do appropriate actions
				} else {
					// do appropriate actions
				}
				model.addAttribute("userLoggedIn", userDTO);
				return "index";
			} else {
				System.out.println("Bad Login parameters:!!!");
				return "login";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "badlogin";
	}

	/*
	 * @RequestMapping(value = "/login", method = RequestMethod.GET) public
	 * ModelAndView login(@RequestParam(value = "error", required = false)
	 * String error,
	 * 
	 * @RequestParam(value = "logout", required = false) String logout,
	 * 
	 * @RequestParam(value = "username") String email, @RequestParam(value =
	 * "password") String password) { ModelAndView model = new ModelAndView();
	 * if (error != null) { model.addObject("error",
	 * "Invalid username and password!"); }
	 * 
	 * if (logout != null) { model.addObject("msg",
	 * "You've been logged out successfully."); } try { LSUserDTO userId =
	 * userFacade.getUserByEmail(email).get();
	 * 
	 * LSUserDTO userDTO = new LSUserDTO(); userDTO.setId(userId.getId());
	 * userDTO.setEmail(email); userDTO.setPasswordHash(password);
	 * userDTO.setUserRole(userId.getUserRole());
	 * 
	 * boolean isUserValid = userFacade.authenticate(userDTO); if (isUserValid)
	 * { if (userFacade.isAdmin(userDTO)) { // do appropriate actions } else {
	 * // do appropriate actions } // model.addAttribute("userLoggedIn",
	 * userDTO); model.addObject("userLoggedIn", email);
	 * model.setViewName("index"); return model; } else { System.out.println(
	 * "Bad Login parameters:!!!"); return model; } } catch (Exception e) {
	 * e.printStackTrace(); } model.setViewName("index"); return model; }
	 */
}
