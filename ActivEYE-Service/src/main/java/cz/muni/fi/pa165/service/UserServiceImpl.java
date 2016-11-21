package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.activeye.dao.UserDao;
import cz.muni.fi.pa165.activeye.entities.User;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Collection;

/**
 * User´s service implementation
 * @author Filip Dubnička [445647]
 */
@Service
public class UserServiceImpl implements UserService {

    @Inject
    private UserDao userDao;

    @Override
    public void registerUser(User u, String password) {
        u.setPasswordHash(BCrypt.hashpw(password, BCrypt.gensalt()));
        userDao.create(u);
    }

    @Override
    public boolean authenticate(User u, String password) {
        return BCrypt.checkpw(password, u.getPasswordHash());
    }

    @Override
    public void updateUser(User u) {
        userDao.update(u);
    }

    @Override
    public void deleteUser(User u) {
        userDao.delete(u);
    }

    @Override
    public User findUserById(Long userId) {
        return userDao.findUserById(userId);
    }

    @Override
    public User findUserByEmail(String email) {
        return userDao.findUserByEmail(email);
    }

    @Override
    public Collection<User> getAllUsers() {
        return userDao.findAll();
    }
}
