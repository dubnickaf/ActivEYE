package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.activeye.dao.RecordDao;
import cz.muni.fi.pa165.activeye.dao.UserDao;
import cz.muni.fi.pa165.activeye.entities.Record;
import cz.muni.fi.pa165.activeye.entities.User;
import cz.muni.fi.pa165.activeye.exceptions.ActiveyeDataAccessException;
import org.springframework.dao.DataAccessException;
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
        if (u.getEmailAddress() == null){
            throw new IllegalArgumentException("Cannot register user with inserted null email address.");
        }
        if (password == null) {
            throw new IllegalArgumentException("Cannot register user with inserted null password.");
        }
        u.setPasswordHash(BCrypt.hashpw(password, BCrypt.gensalt()));
        try {
            userDao.create(u);
        } catch (Exception e) {
            throw new ActiveyeDataAccessException("Problem on DAO layer",e);
        }
    }

    @Override
    public boolean authenticate(User u, String password) {
        if (u == null) {
            throw new IllegalArgumentException("Cannot authenticate inserted null user.");
        }
        if (u.getEmailAddress() == null){
            throw new IllegalArgumentException("Cannot register user with inserted null email address.");
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
        try{
            userDao.update(u);
        } catch (Exception e) {
            throw new ActiveyeDataAccessException("Problem on DAO layer",e);
        }
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
            try{
                recordDao.deleteRecord(record);
            } catch (Exception e) {
                throw new ActiveyeDataAccessException("Problem on DAO layer",e);
            }
        }
        try{
            userDao.delete(u);
        } catch (Exception e) {
            throw new ActiveyeDataAccessException("Problem on DAO layer",e);
        }
    }

    @Override
    public User findUserById(Long userId) {
        if (userId == null) {
            throw new IllegalArgumentException("Cannot find user with null id.");
        }
        try{
           return userDao.findUserById(userId);
        } catch (Exception e) {
            throw new ActiveyeDataAccessException("Problem on DAO layer",e);
        }
    }

    @Override
    public User findUserByEmail(String email) {
        if (email == null) {
            throw new IllegalArgumentException("Cannot find user with null email.");
        }
        try{
            return userDao.findUserByEmail(email);
        } catch (Exception e) {
            throw new ActiveyeDataAccessException("Problem on DAO layer",e);
        }
    }

    @Override
    public Collection<User> getAllUsers() {
        try{
            return userDao.findAll();
        } catch (Exception e) {
            throw new ActiveyeDataAccessException("Problem on DAO layer",e);
        }
    }
}
