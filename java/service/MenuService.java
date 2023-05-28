package mirosimo.car_showroom2.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mirosimo.car_showroom2.model.MenuItem;
import mirosimo.car_showroom2.repository.MenuRepository;

@Service
public class MenuService {
	@Autowired
	private MenuRepository menuRepository;
	
	public List<MenuItem> getMenuTree() {				
		
	  	return menuRepository.getMenuTree();
	}
	
	public MenuItem getMenuById(long id) {		
		Optional<MenuItem> optional = this.menuRepository.findById(id);

		MenuItem menu = null;
		if (optional.isPresent()) {
			menu = optional.get();
		} else {
			throw new RuntimeException(" Not found menu ID: " + id);
		}		
		return menu;
	}
}
