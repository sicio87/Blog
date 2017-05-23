package eu.karols.service.post;

import java.util.List;
import java.util.Optional;

import eu.karols.domain.Post;
import eu.karols.form.PostCreateForm;

public interface PostService {
	List<Post> findAll();
	Optional<Post> findById(Long id);
	Post create(PostCreateForm postForm);
	Post edit(Post post);

	void deleteById(Long id);
}
