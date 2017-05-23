package eu.karols.service.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import eu.karols.domain.User;
import eu.karols.form.UserCreateForm;
import eu.karols.dao.UserDAO;

import java.util.Collection;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserDAO userDAO;

    @Autowired
    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public Optional<User> getUserById(long id) {
        LOGGER.debug("Getting user={}", id);
        return Optional.ofNullable(userDAO.findOne(id));
    }

    @Override
    public Optional<User> getUserByLogin(String login) {
        LOGGER.debug("Getting user by login={}", login);
        return userDAO.findOneByLogin(login);
    }
    
    @Override
    public Optional<User> getUserByName(String name) {
        LOGGER.debug("Getting user by name={}", name);
        return userDAO.findOneByName(name);
    }
    
    @Override
    public Optional<User> getUserByEmail(String email) {
        LOGGER.debug("Getting user by email={}", email.replaceFirst("@.*", "@***"));
        return userDAO.findOneByEmail(email);
    }

    @Override
    public Collection<User> getAllUsers() {
        LOGGER.debug("Getting all users");
        return userDAO.findAll(new Sort("login"));
    }

    @Override
    public User create(UserCreateForm userForm) {
        User user = new User();
        user.setLogin(userForm.getLogin());
        user.setName(userForm.getName());
        user.setEmail(userForm.getEmail());
        user.setPassword(new BCryptPasswordEncoder().encode(userForm.getPassword()));
        user.setRole(userForm.getRole());
        return userDAO.save(user);
    }

	@Override
	public User findUserByLogin(String login) {
		return userDAO.findUserByLogin(login);
	}

	@Override
	public void deleteById(Long id) {
		this.userDAO.delete(id);
	}
}