package cz.muni.fi.pa165.facades;

import cz.muni.fi.pa165.activeye.dto.NotAuthenticatedUserDTO;
import cz.muni.fi.pa165.activeye.dto.UserDTO;
import cz.muni.fi.pa165.activeye.entities.User;
import cz.muni.fi.pa165.activeye.facades.UserFacade;
import cz.muni.fi.pa165.mapping.BeanMappingService;
import cz.muni.fi.pa165.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.Collection;

/**
 * User´s facade
 * @author Filip Dubnička [445647]
 */
@Service
@Transactional
public class UserFacadeImpl implements UserFacade{

    @Inject
    UserService userService;

    @Inject
    BeanMappingService beanMappingService;

    @Override
    public void registerUser(UserDTO u, String password) {
        User user = beanMappingService.mapTo(u, User.class);
        userService.registerUser(user,password);
        u.setId(user.getId());
    }

    @Override
    public boolean authenticate(NotAuthenticatedUserDTO u) {
        User user = userService.findUserByEmail(u.getEmail());
        return userService.authenticate(user, u.getPassword());
    }

    @Override
    public void updateUser(UserDTO u) {
        User user = beanMappingService.mapTo(u, User.class);
        userService.updateUser(user);
    }

    @Override
    public void deleteUser(UserDTO u) {
        User user = beanMappingService.mapTo(u, User.class);
        userService.deleteUser(user);
    }

    @Override
    public UserDTO findUserById(Long userId) {
        User user = userService.findUserById(userId);
        return (user == null) ? null : beanMappingService.mapTo(user, UserDTO.class);
    }

    @Override
    public UserDTO findUserByEmail(String email) {
        User user = userService.findUserByEmail(email);
        return (user == null) ? null : beanMappingService.mapTo(user, UserDTO.class);
    }

    @Override
    public Collection<UserDTO> getAllUsers() {
        Collection<User> users = userService.getAllUsers();
        return (users == null) ? null : beanMappingService.mapTo(users, UserDTO.class);
    }
}
