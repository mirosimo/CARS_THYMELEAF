package com.mirosimo.car_showroom.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.mirosimo.car_showroom.model.Course;
import com.mirosimo.car_showroom.model.Employee;
import com.mirosimo.car_showroom.repository.CourseRepository;

@Service
public class CourseService {
	@Autowired 
	private CourseRepository courseRepository;
	
	public List<Course> getAllEntities() {
		return courseRepository.findAll();
	}
	
	public void saveEntity(Course entity) {
		this.courseRepository.save(entity);
	}
			
	public void deleteEntityById(long id) {
		this.courseRepository.deleteById(id);
	}
	
	public Course getCourseById(long id) {
		Optional<Course> optional = this.courseRepository.findById(id);
		Course course = null;
		if (optional.isPresent()) {
			course = optional.get();
		} else {
			throw new RuntimeException(" Not found course ID: " + id);
		}
		return course;
	}
}
