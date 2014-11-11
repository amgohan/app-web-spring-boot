package com.entreprise.web.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.entreprise.web.utils.ApplicationURIs;
import com.entreprise.web.utils.ViewURIsMapping;


/**
 * @author Sidi Amine
 *
 */
@Controller
public class GlobalController {
	
	@RequestMapping(ApplicationURIs.HOME)
    String index(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User user = (User) authentication.getPrincipal();
		String username = "";
		if(user != null) {
			username = user.getUsername();
		}
		model.addAttribute("username", username);
        return ViewURIsMapping.HOME.getView();
    }

}
