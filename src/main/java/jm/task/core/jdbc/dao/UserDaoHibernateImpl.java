package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import javax.persistence.criteria.CriteriaDelete;
import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        try (Session session = Util.getConnectionHibernate().openSession()) {
            session.beginTransaction();
            session.createSQLQuery("CREATE TABLE IF NOT EXISTS USERS (" +
                    "ID BIGINT(20) unsigned NOT NULL AUTO_INCREMENT, " +
                    "NAME VARCHAR(45) NOT NULL, " +
                    "LAST_NAME VARCHAR(45) NOT NULL, " +
                    "AGE TINYINT(3) NOT NULL, " +
                    "PRIMARY KEY (ID))")
                    .addEntity(User.class)
                    .executeUpdate();
            session.getTransaction().commit();
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = Util.getConnectionHibernate().openSession()) {
            session.beginTransaction();
            session.createSQLQuery("DROP TABLE IF EXISTS USERS").addEntity(User.class).executeUpdate();
            session.getTransaction().commit();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = Util.getConnectionHibernate().openSession()) {
            session.beginTransaction();

            User user = new User();
            user.setName(name);
            user.setLastName(lastName);
            user.setAge(age);
            session.save(user);

            session.getTransaction().commit();
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = Util.getConnectionHibernate().openSession()) {
            session.beginTransaction();
            User user = session.find(User.class, id);
            session.remove(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Ошибка удаления данных по ключу.");
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users;
        try (Session session = Util.getConnectionHibernate().openSession()) {
            session.beginTransaction();
            users = session.createQuery("SELECT User FROM User User", User.class).getResultList();
            if (users.size() < 1) {
                throw new Exception();
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("В таблице нет данных.");
            return new ArrayList<>();
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = Util.getConnectionHibernate().openSession()) {
            session.beginTransaction();
            //session.createQuery("DELETE FROM User").executeUpdate();

            CriteriaDelete<User> delete = session.getCriteriaBuilder().createCriteriaDelete(User.class);
            delete.from(User.class);
            session.createQuery(delete).executeUpdate();

            session.getTransaction().commit();
        }
    }
}