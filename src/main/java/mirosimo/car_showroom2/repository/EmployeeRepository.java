package mirosimo.car_showroom2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mirosimo.car_showroom2.model.Employee;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{

}

