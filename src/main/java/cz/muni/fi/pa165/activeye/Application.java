package cz.muni.fi.pa165.activeye;

import cz.muni.fi.pa165.activeye.dao.UserDao;
import cz.muni.fi.pa165.activeye.dao.impl.UserDaoImpl;
import cz.muni.fi.pa165.activeye.entities.User;
import cz.muni.fi.pa165.activeye.enums.Gender;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.lang.annotation.Annotation;

/**
 * Created by dubnickaf@gmail.com [445647] on windows user "Toshiba" on 22.10.2016.
 */
public class Application {
    /**
     * Main method, used to run the application.
     * @param args
     * @author dubnickaf@gmail.com [445647]
     */
    public static void main(String[] args){
        System.out.println("Running ActivEYE");

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(InMemoryDatabaseSpring.class,PersistanceContext.class);
        EntityManager em = applicationContext.getBean(EntityManager.class);

        /*
        Ak chcete vyskusat funkcionalitu svojho DAOIMPL tak takto podobne:
        UserDao userDao = applicationContext.getBean(UserDao.class);
        User user = new User();
        user.setName("Peter Jozef");
        user.setGender(Gender.MALE);
        String email = "test@vv.sk";
        user.setEmailAddress(email);
        userDao.create(user); //this method must be @Transactional
        User user1 = userDao.findUserByEmail(email);
        System.out.println("Name: " + user1.getName());*/

        System.out.println("ActivEYE stopped");
    }
}
