package com.mirosimo.car_showroom.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mirosimo.car_showroom.model.Course;

public interface TrainingCourseRepository extends JpaRepository<Course, Long>{ 

}
