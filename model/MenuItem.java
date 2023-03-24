package com.mirosimo.car_showroom.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

/*
 *  Represent one menu item from menu tree 
 */
@Entity
@Table(name="menu")
public class MenuItem {
	@Id
	@SequenceGenerator(
			name = "menu_sequence",
			sequenceName = "menu_sequence",
			allocationSize = 1
	)
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "menu_sequence"
	)
	private long id;
	private String itemName;
	private String itemDesc;
	
	/* 
	 * Field for url which is launched when user clicks on menu item 
	 * 
	 * */
	private String url;
	
	
	private String active;	
	private int itemOrder;		// Used for order menuItem siblings		
	
	@ManyToOne
	@JoinColumn(name = "id_parent")
	private MenuItem parentMenuItem;
	
	@OneToMany(mappedBy = "parentMenuItem", 
				fetch = FetchType.EAGER, 
				cascade = {CascadeType.ALL})
	@Where(clause = "active = 'Y'")
	@OrderBy("item_order")
	private List<MenuItem> nestedItems;
	
	public MenuItem() {
		super();
	}		


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemDesc() {
		return itemDesc;
	}

	public void setItemDesc(String itemDesc) {
		this.itemDesc = itemDesc;
	}

	public int getItemOrder() {
		return itemOrder;
	}

	public void setItemOrder(int itemOrder) {
		this.itemOrder = itemOrder;
	}

	public List<MenuItem> getNestedItems() {
		return nestedItems;
	}

	public void setNestedItems(List<MenuItem> nestedItems) {
		this.nestedItems = nestedItems;
	}

	public MenuItem getParentMenuItem() {
		return parentMenuItem;
	}

	public void setParentMenuItem(MenuItem parentMenuItem) {
		this.parentMenuItem = parentMenuItem;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
