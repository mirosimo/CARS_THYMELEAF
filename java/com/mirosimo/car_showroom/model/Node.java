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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.sun.istack.NotNull;

@Entity
@Table
public class Node {
	@Id
	@SequenceGenerator(
			name = "node_sequence",
			sequenceName = "node_sequence",
			allocationSize = 1
	)
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "node_sequence"
	)
    private Long id;

    @NotNull
    private String name;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Node parentNode;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "parentNode", 
    		cascade = { CascadeType.ALL} )
               //cascade = { CascadeType.MERGE, CascadeType.PERSIST} )
    private List<Node> children;

    
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Node getParentNode() {
		return parentNode;
	}

	public void setParentNode(Node parentNode) {
		this.parentNode = parentNode;
	}

	public List<Node> getChildren() {
		return children;
	}

	public void setChildren(List<Node> children) {
		this.children = children;
	}
}
