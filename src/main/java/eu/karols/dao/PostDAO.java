package eu.karols.dao;

import eu.karols.domain.Post;
import eu.karols.domain.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostDAO extends JpaRepository<Post, Long> {
	Post findPostById(Long id);
	Optional<Post> findOneById(Long id);
	List<Post> findAll();
	List<Post> findAllByAuthor(User author);
}