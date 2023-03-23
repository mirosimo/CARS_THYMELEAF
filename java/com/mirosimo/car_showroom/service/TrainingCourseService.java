package com.mirosimo.car_showroom.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mirosimo.car_showroom.model.TrainingCourse;
import com.mirosimo.car_showroom.repository.TrainingCourseRepository;

@Service
public class TrainingCourseService {
	
	@Autowired
	private TrainingCourseRepository courseRepository;		

	public List<TrainingCourse> getAllTrainingCourses() {
		return courseRepository.findAll();
	}
	
	public TrainingCourse getCourseById(long id) {
		Optional<TrainingCourse> optional = this.courseRepository.findById(id);
		TrainingCourse course = null;
		if (optional.isPresent()) {
			course = optional.get();
		} else {
			throw new RuntimeException(" Not found course ID: " + id);
		}
		return course;
	}
	
	public TrainingCourse saveCourse(TrainingCourse course) {
		return this.courseRepository.save(course);
	}
}
