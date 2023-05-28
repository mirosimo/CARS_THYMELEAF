package mirosimo.car_showroom2.controller;

import java.util.Date;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import mirosimo.car_showroom2.model.Role;
import mirosimo.car_showroom2.model.User;
import mirosimo.car_showroom2.model.UserRole;
import mirosimo.car_showroom2.service.RoleService;
import mirosimo.car_showroom2.service.UserRoleService;
import mirosimo.car_showroom2.service.UserService;




@Controller
public class UserController {
	@Autowired
	UserService userService;	       
    
	@Autowired
	RoleService roleService;
	
	@Autowired
	UserRoleService userRoleService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
		
    
    @GetMapping("/user-new")
    public String newUserMoreRoles(Model model) {
    	model.addAttribute("appUser", new User());
    	
    	/* Pass all active roles into Thymeleaf */
    	model.addAttribute("listRoles", roleService.getAllEntities().stream()
    										.filter(role -> role.isActive()).collect(Collectors.toList()));
    	return "user-new";
    }
    
    
    /* Saves User into database. */
    @PostMapping("/save-user")
    public String saveUser(Model model, @ModelAttribute("appUser") User user,
    		@RequestParam("password") String passw,
    		@RequestParam("role_checkbox") int[] roles) {    	    	
    	
    	// Encoding password
    	user.setPassword(passwordEncoder.encode(passw));
    	
    	/*
    	 *  User must be saved - because user ID is generated after added to db
    	 *  Id's are automatically generated in db, not in application
    	 *  
    	 */    	 
    	User userSaved = this.userService.saveEntity(user);    	

    	for (int roleId : roles) {
    		Role role = roleService.getRoleById(roleId);
    		
    		/* connection entity between User and Role - relation M : N */
        	UserRole userRole = new UserRole();
        	
        	/* Setting the keys entities */
        	userRole.setUser(userSaved);
        	userRole.setRole(role);
        	        	
        	/* Setting Extra fields in connection entity UserRole */
        	userRole.setActive(true);
        	userRole.setDateAdded(new Date());
        	
        	/* Saving */
        	userRoleService.saveEntity(userRole);
    	}    	    	        	
    	    	
    	return "redirect:/user-list";
    }
    
	/* View displays list of users - in table form */
	@GetMapping("/user-list")
	public String listUserView(Model model) {
		model.addAttribute("listUsers", userService.getAllEntities());
		return "user-list";
	}
	
	
	@GetMapping("/user-role-list")
	public String listUserRoleView(Model model) {
		/* Show just users in active state */
		model.addAttribute("listUsers", userService.getAllEntities().stream()
								.filter(user -> user.isActive()).collect(Collectors.toList()));
		return "user-role-list";
	}
	
	/* Delete user */
	@GetMapping("/user-delete/{id}")
	public String deleteUser(@PathVariable (value="id") long id) {
		this.userService.deleteEntityById(id);
		return "redirect:/user-list";
	}
}
