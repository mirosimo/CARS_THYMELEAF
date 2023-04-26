package mirosimo.car_showroom2.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import mirosimo.car_showroom2.model.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {

}
