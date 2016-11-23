package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.activeye.dao.RecordDao;
import cz.muni.fi.pa165.activeye.dao.UserDao;
import cz.muni.fi.pa165.activeye.entities.Record;
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

    @Inject
    private RecordDao recordDao;

    @Override
    public void registerUser(User u, String password) {
        if (u == null) {
            throw new IllegalArgumentException("Cannot register inserted null user.");
        }
        if (password == null) {
            throw new IllegalArgumentException("Cannot register user with inserted null password.");
        }
        u.setPasswordHash(BCrypt.hashpw(password, BCrypt.gensalt()));
        userDao.create(u);
    }

    @Override
    public boolean authenticate(User u, String password) {
        if (u == null) {
            throw new IllegalArgumentException("Cannot authenticate inserted null user.");
        }
        if (password == null) {
            throw new IllegalArgumentException("Cannot authenticate user with inserted null password.");
        }
        return BCrypt.checkpw(password, u.getPasswordHash());
    }

    @Override
    public void updateUser(User u) {
        if (u == null) {
            throw new IllegalArgumentException("Cannot update inserted null user.");
        }
        userDao.update(u);
    }

    @Override
    public void deleteUser(User u) {
        if (u == null) {
            throw new IllegalArgumentException("Cannot update inserted null user.");
        }
        if (u.getId() == null) {
            throw new IllegalArgumentException("User has not been successfully persisted yet");
        }
        for (Record record : u.getActivityRecords()){
            recordDao.deleteRecord(record);
        }
        userDao.delete(u);
    }

    @Override
    public User findUserById(Long userId) {
        if (userId == null) {
            throw new IllegalArgumentException("Cannot find user with null id.");
        }
        return userDao.findUserById(userId);
    }

    @Override
    public User findUserByEmail(String email) {
        if (email == null) {
            throw new IllegalArgumentException("Cannot find user with null email.");
        }
        return userDao.findUserByEmail(email);
    }

    @Override
    public Collection<User> getAllUsers() {
        return userDao.findAll();
    }
}
