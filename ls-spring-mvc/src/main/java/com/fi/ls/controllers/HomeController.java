package com.fi.ls.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
