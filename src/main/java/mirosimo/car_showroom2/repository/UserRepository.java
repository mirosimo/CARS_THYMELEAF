package mirosimo.car_showroom2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mirosimo.car_showroom2.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByUsername(String username);
}
