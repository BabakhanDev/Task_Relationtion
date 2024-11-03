package hibernate_task.dao.impl;

import hibernate_task.config.DatabaseConnection111;
import hibernate_task.dao.UserDao;
import hibernate_task.entity.User;
import jakarta.persistence.EntityManager;
import org.postgresql.shaded.com.ongres.stringprep.Profile;

public class UserDaoImpl implements UserDao, AutoCloseable {
    private final EntityManager em = DatabaseConnection111.getEntityManager().createEntityManager();

    @Override
    public String saveUser(User user) {
        try {
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
            return "Saved";
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new RuntimeException(e);
        }
    }

    @Override
    public User findById(Long id) {
        try {
            em.getTransaction().begin();
            User user = em.find(User.class, id);
            em.getTransaction().commit();
            return user;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new RuntimeException(e);
        }
    }

    @Override
    public User updateUserProfile(Long userId, Profile profile) {
        try {
            em.getTransaction().begin();
            User user = em.find(User.class, userId);
            if (user != null) {
                user.setProfile((hibernate_task.entity.Profile) profile);
                em.merge(user);
                em.getTransaction().commit();
            return user;
            }else {
                em.getTransaction().rollback();
                return null;
            }
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new RuntimeException(e);
        }
    }

    @Override
    public String deleteUser() {
        try {
            em.getTransaction().begin();
            User user = em.find(User.class, 1);
            if (user != null) {
                em.remove(user);
                em.getTransaction().commit();
                return "Deleted";
            }else {
                em.getTransaction().rollback();
                return "User not found";
            }
        }catch (Exception e) {
            em.getTransaction().rollback();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() throws Exception {
        em.close();
    }
}
