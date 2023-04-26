package mirosimo.car_showroom2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import mirosimo.car_showroom2.model.MenuItem;

@Repository
public interface MenuRepository extends JpaRepository<MenuItem, Long> {
	
	
	/*
	 * This query returns rows from menu table. The returned order of rows is in hierarchical sequence
	 * This query result is usable for menu creation in view.  
	 */	
	String myQuery = "SELECT id, id_parent, item_name, nvl(item_desc,'') item_desc, nvl(item_order,0) item_order, LEVEL item_level FROM "
			+ "    menu "
			+ "    START WITH id=1 "
			+ "    CONNECT BY PRIOR menu.id = menu.id_parent "
			+ "    ORDER SIBLINGS BY item_order";	
	@Query(nativeQuery = true, value = myQuery)	
	public List<MenuItem> getMenuTree();
	
	//@Query(nativeQuery = false, value = "select n from Menu n where n.id = ?")
	//Menu findMenyById(Long id);
	
	
}
