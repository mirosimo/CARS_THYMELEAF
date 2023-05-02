package mirosimo.car_showroom2.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import mirosimo.car_showroom2.model.User;
import mirosimo.car_showroom2.service.UserService;

/* **************************************************/
/*    Security is just now in development state ... */
/* **************************************************/
@Controller
public class UserController {
	@Autowired
	UserService userService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	
    @RequestMapping("/login")
    public String login(Model model, 
    					@RequestParam(value = "error",required = false) String error,
                        @RequestParam(value = "logout",required = false) String logout) 
    {
        String errorMessage=null;
        if(error!=null ){
            errorMessage="Username/password is incorrect.";
        }
        if(logout!=null){
            errorMessage="You have logout successfully";
        }
        model.addAttribute("errorMessage",errorMessage);
        return "login";
    }
    
    
    
    @GetMapping("/user-new")
    public String newUser(Model model) {
    	model.addAttribute("appUser", new User());
    	return "user-new";
    } 
    
    @PostMapping("/save-user")
    public String saveUser(@Valid User user, BindingResult result, Model model,
			@RequestParam("password") String passw) {
    	if (result.hasErrors()) {
			model.addAttribute("appUser", user);
			return "user-new";
		}
    	user.setPassword(passwordEncoder.encode(passw));
    	this.userService.saveEntity(user);
    	return "redirect:/user-new";
    }
        
}
