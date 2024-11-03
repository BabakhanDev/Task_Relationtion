package hibernate_task.service;
import hibernate_task.dao.UserDao;
import hibernate_task.dao.impl.UserDaoImpl;
import hibernate_task.entity.User;
import org.postgresql.shaded.com.ongres.stringprep.Profile;

public interface UserService {
    String saveUser(User user);
    User findById(Long id);
    User updateUserProfile(Long userId, Profile profile);
    String deleteUser();
}
