package mirosimo.car_showroom2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {
	
	// public end points
	@GetMapping({"/", "/home", "/logout"})
	public String getHome() {
		return "home";
	}
	
	// public end point
	@GetMapping("/login")
	public String login() {
		return "login";
	}	
	

		
	/*  
	 *  Redirecting to login view
	 *  Is used when user visit the public page and is not logged in. 
	 *  In this case in app appears login button which runs this end point.
	 *  And redirected user to login page by clicking the button.
	 *     
	 */	
	@PostMapping("/login-view")
	public String loginView() {
		return "redirect:/login";
	}
}
