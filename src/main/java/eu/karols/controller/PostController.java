package eu.karols.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import eu.karols.domain.Post;
import eu.karols.domain.User;
import eu.karols.form.PostCreateForm;
import eu.karols.service.post.PostService;
import eu.karols.service.user.UserService;

@Controller
public class PostController {
	private static final Logger LOGGER = LoggerFactory.getLogger(PostController.class);
	
	private final PostService postService;
	
	private final UserService userService;

    @Autowired
    public PostController(PostService postService, UserService userService) {
        this.postService = postService;
		this.userService = userService;
    }
	
    @PreAuthorize("hasAuthority('ROLE_ADMIN') OR hasAuthority('ROLE_USER')")
    @RequestMapping(value = "/post/create", method = RequestMethod.GET)
    public ModelAndView getPostCreatePage(Model model) {
        LOGGER.debug("Getting post create form");
        return new ModelAndView("post/postCreate", "form", new PostCreateForm());
    }	

    @PreAuthorize("hasAuthority('ROLE_ADMIN') OR hasAuthority('ROLE_USER')")
    @RequestMapping(value = "/post/create", method = RequestMethod.POST)
    public String savePost(@ModelAttribute("form") PostCreateForm form){
    	LOGGER.info("Save post " + form.getTitle());
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	User author = userService.findUserByLogin(auth.getName());
		form.setAuthor(author);
        postService.create(form);

        return "redirect:/posts";
    }
    
	@PreAuthorize("hasAuthority('ROLE_ADMIN') OR hasAuthority('ROLE_USER')")
	@RequestMapping("/post/delete/{id}")
	public String deletePost(@PathVariable("id") Long id) {
		LOGGER.debug("Deleted post " + id);
	    postService.deleteById(id);
	    return "redirect:/posts";
	}
	
    @PreAuthorize("hasAuthority('ROLE_ADMIN') OR hasAuthority('ROLE_USER')")
    @RequestMapping(value = "/post/edit/{id}", method = RequestMethod.GET)
    public ModelAndView getPostEditPage(Model model, @PathVariable("id") Long id) {
        LOGGER.debug("Getting post edit form");
        
        return new ModelAndView("post/postEdit", "post", postService.findById(id));
    }
    
    @PreAuthorize("hasAuthority('ROLE_ADMIN') OR hasAuthority('ROLE_USER')")
    @RequestMapping(value = "/post/edit", method = RequestMethod.POST)
    public String editPost(@ModelAttribute("form") Post post){
    	LOGGER.info("Edit post " + post.getTitle());
		postService.edit(post);

        return "redirect:/posts";
    }
}
