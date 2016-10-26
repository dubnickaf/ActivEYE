package cz.muni.fi.pa165.activeye;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.persistence.EntityManager;

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
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(InMemoryDatabaseSpring.class, PersistenceContext.class);
        EntityManager em = applicationContext.getBean(EntityManager.class);


        //Ak chcete vyskusat funkcionalitu svojho DAOIMPL tak takto podobne:
        /*UserDao userDao = applicationContext.getBean(UserDao.class);
        User user = new User();
        user.setName("Peter Jozef");
        user.setGender(Gender.MALE);
        String email = "test@vv.sk";
        user.setEmailAddress(email);
        userDao.create(user); //this method must be @Transactional
        User user1 = userDao.findUserByEmail(email);
        System.out.println("Name: " + user1.getName());*/

        // Side testy na ActivityDao, ked to bude zavadzat tak to vyhodte...
        /*ActivityDao activityDao = applicationContext.getBean(ActivityDao.class);
        Activity a = new Activity();
        a.setName("Utekanie");
        a.setCaloriesRatio(new BigDecimal("0.95"));
        activityDao.create(a);
        Activity found = activityDao.findByName("Utekanie");
        System.out.println(found.toString());

        found.setName("Testovanie");
        found.setCaloriesRatio(new BigDecimal("0.66"));
        activityDao.update(found);
        System.out.println(activityDao.findByName("Testovanie").toString());

        Activity a1 = new Activity();
        a1.setName("Lukostrelba");
        a1.setCaloriesRatio(new BigDecimal("0.01"));
        activityDao.create(a1);
        System.out.println(activityDao.findAll().toString());

        activityDao.delete(a);
        System.out.println(activityDao.findAll().toString());

        System.out.println("Find by id: " + activityDao.findById(2l).toString());
        */

        System.out.println("ActivEYE stopped");
    }
}
