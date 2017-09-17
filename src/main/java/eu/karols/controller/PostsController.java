package eu.karols.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import eu.karols.service.post.PostService;

@Controller
public class PostsController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PostsController.class);
    private final PostService postService;
    
    @Autowired
    public PostsController(PostService postService) {
		this.postService = postService;
    }
    
    @PreAuthorize("hasAuthority('ROLE_ADMIN') OR hasAuthority('ROLE_USER')")
    @RequestMapping("/posts")
    public ModelAndView getPostsPage() {
        LOGGER.debug("Getting posts page");
        return new ModelAndView("post/posts", "posts", postService.findAll());
    }
}
