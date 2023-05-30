package mirosimo.car_showroom2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import mirosimo.car_showroom2.model.Role;
import mirosimo.car_showroom2.service.RoleService;



public class RoleController {
	@Autowired
	RoleService roleService;
	
    @GetMapping("/role-new")
    public String newUser(Model model) {
    	model.addAttribute("appRole", new Role());
    	return "role-new";
    }
             
    
    @PostMapping("/role-save")
    public String saveUser(Model model, @ModelAttribute("appRole") Role role) {    	    	    	
    	this.roleService.saveEntity(role);    	
    	return "redirect:/role-new";
    }
    
    @GetMapping("/role-list")
    public String roleList(Model model) {    	    	    	
    	model.addAttribute("listRoles", roleService.getAllEntities());    	
    	return "role-list";
    }
}
