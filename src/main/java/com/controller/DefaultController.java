/**
 * 
 */
package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.common.Util;

/**
 * @author virens
 *
 */
@Controller
@RequestMapping("/home")
public class DefaultController {

	@RequestMapping("/default")
	public String home(ModelMap model) {
		 model.addAttribute("username", Util.getCurrentUsername());
		return "default";
	}
	
}
