package com.mirosimo.car_showroom.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mirosimo.car_showroom.model.Node;
import com.mirosimo.car_showroom.repository.NodeRepository;

@Service
public class NodeService {
	@Autowired
	private NodeRepository nodeRepository;
	
	public Node getNodeById(long id) {
		Optional<Node> optional = this.nodeRepository.findById(id);
		Node node = null;
		if (optional.isPresent()) {
			node = optional.get();
		} else {
			throw new RuntimeException(" Not found employee ID: " + id);
		}
		return node;
	}
}
