package mirosimo.car_showroom2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
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
}
