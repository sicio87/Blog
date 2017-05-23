package eu.karols.service.post;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eu.karols.dao.PostDAO;
import eu.karols.domain.Post;
import eu.karols.form.PostCreateForm;

@Service
public class PostServiceImpl implements PostService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PostServiceImpl.class);
    private final PostDAO postDAO;

    @Autowired
    public PostServiceImpl(PostDAO postDAO) {
        this.postDAO = postDAO;
    }

	@Override
	public List<Post> findAll() {
		LOGGER.debug("Getting all posts");
		return postDAO.findAll();
	}

	@Override
	public Optional<Post> findById(Long id) {
		LOGGER.debug("Getting post by id={}", id);
		return postDAO.findOneById(id);
	}

	@Override
	public Post create(PostCreateForm postForm) {
		LOGGER.debug("Create new post");
		Post post = new Post();
		post.setAuthor(postForm.getAuthor());
		post.setTitle(postForm.getTitle());
		post.setBody(postForm.getBody());
		post.setDate(postForm.getDate());
		
		return postDAO.save(post);
	}

	@Override
	public Post edit(Post post) {
		return postDAO.save(post);
	}

	@Override
	public void deleteById(Long id) {
		postDAO.delete(id);
		
	}
}
