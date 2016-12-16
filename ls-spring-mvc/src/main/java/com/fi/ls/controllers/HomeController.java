package com.fi.ls.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Pavel Šeda (441048)
 *
 */
@Controller
public class HomeController {

	@RequestMapping(value = "/errorpage")
	public String errorpage() {
		return "errorpage";
	}

}
