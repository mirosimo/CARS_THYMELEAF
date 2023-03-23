package com.mirosimo.car_showroom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mirosimo.car_showroom.model.Node;

@Repository
public interface NodeRepository  extends JpaRepository<Node, Long> {

}

