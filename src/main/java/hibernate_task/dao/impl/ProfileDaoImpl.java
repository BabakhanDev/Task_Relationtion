package hibernate_task.dao.impl;

import hibernate_task.config.DatabaseConnection111;
import hibernate_task.dao.ProfileDao;
import hibernate_task.entity.User;
import jakarta.persistence.EntityManager;
import org.postgresql.shaded.com.ongres.stringprep.Profile;

public class ProfileDaoImpl implements ProfileDao, AutoCloseable {
    private final EntityManager em = DatabaseConnection111.getEntityManager().createEntityManager();

    @Override
    public String saveProfile(User userId, Profile profile) {
        try {
            em.getTransaction().begin();
            User user = em.find(User.class, userId);
            if (user != null) {
                user.setProfile((hibernate_task.entity.Profile) profile);
                em.merge(user);
            } else {
                throw new RuntimeException("User not found");
            }
            em.getTransaction().commit();
            return "Profile saved";
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new RuntimeException(e);
        }
    }

    @Override
    public Profile findProfileByUserId(Long userId) {
        try {
            em.getTransaction().begin();
            User user = em.find(User.class, userId);
            em.getTransaction().commit();
            return user != null ? (Profile) user.getProfile() : null;

        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new RuntimeException(e);
        }
    }

    @Override
    public String deleteProfileByUserId(Long userId) {
        try {
            em.getTransaction().begin();
            User user = em.find(User.class, userId);
            if (user != null && user.getProfile() != null) {
                em.remove(user.getProfile());
                user.setProfile(null);
                em.merge(user);
                em.getTransaction().commit();
                return "Profile deleted";
            } else {
                em.getTransaction().rollback();
                return "Profile not found";
            }
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() throws Exception {
        em.close();
    }
}
