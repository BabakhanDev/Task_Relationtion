package hibernate_task.dao;

import hibernate_task.entity.User;
import jakarta.persistence.EntityManagerFactory;
import org.postgresql.shaded.com.ongres.stringprep.Profile;

public interface UserDao {
    String saveUser(User user);
    User findById(Long id);
    User updateUserProfile(Long userId, Profile profile);
    String deleteUser();

}
