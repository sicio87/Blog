package eu.karols.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

import eu.karols.domain.User;

public interface UserDAO extends JpaRepository<User, Long> {
	User findUserByLogin(String login);
	Optional<User> findOneByLogin(String login);
	Optional<User> findOneByName(String name);
	Optional<User> findOneByEmail(String email);
}