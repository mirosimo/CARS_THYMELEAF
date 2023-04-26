package mirosimo.car_showroom2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mirosimo.car_showroom2.model.Department;

public interface DepartmentRepository  extends JpaRepository<Department, Long>{

}
