package com.fi.ls.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Pavel Šeda (441048)
 *
 */
@Controller
public class MenuController {

	@RequestMapping(value = "/index")
	public String index() {
		return "index";
	}	
	
	@RequestMapping(value = "/contact")
	public String contact() {
		return "contact";
	}

	@RequestMapping(value = "/about")
	public String about() {
		return "about";
	}
}
