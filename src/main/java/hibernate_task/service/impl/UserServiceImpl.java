package hibernate_task.service.impl;

import hibernate_task.dao.ProfileDao;
import hibernate_task.dao.UserDao;
import hibernate_task.dao.impl.ProfileDaoImpl;
import hibernate_task.dao.impl.UserDaoImpl;
import hibernate_task.entity.User;
import hibernate_task.service.UserService;
import org.postgresql.shaded.com.ongres.stringprep.Profile;

public class UserServiceImpl implements UserService {
    private final UserDao userDao = new UserDaoImpl();
    private final ProfileDao profileDao = new ProfileDaoImpl();
    @Override
    public String saveUser(User user) {
        userDao.saveUser(user);
        return "Successfully saved user";
    }

    @Override
    public User findById(Long id) {
        return  userDao.findById(id);

    }

    @Override
    public User updateUserProfile(Long userId, Profile profile) {
        return userDao.updateUserProfile(userId, profile);
    }

    @Override
    public String deleteUser() {
        userDao.deleteUser();
        return "Successfully deleted user";
    }

}
