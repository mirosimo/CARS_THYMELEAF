package com.mirosimo.car_showroom.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mirosimo.car_showroom.model.Course;
import com.mirosimo.car_showroom.repository.TrainingCourseRepository;

@Service
public class TrainingCourseService {
	
	@Autowired
	private TrainingCourseRepository courseRepository;		

	public List<Course> getAllTrainingCourses() {
		return courseRepository.findAll();
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
	
	public Course saveCourse(Course course) {
		return this.courseRepository.save(course);
	}
}
