package hibernate_task.service.impl;

import hibernate_task.dao.ProfileDao;
import hibernate_task.dao.impl.ProfileDaoImpl;
import hibernate_task.entity.User;
import hibernate_task.service.ProfileService;
import org.postgresql.shaded.com.ongres.stringprep.Profile;

public class ProfileServiceImpl implements ProfileService {
    private final ProfileDao profileDao = new ProfileDaoImpl();
    @Override
    public String saveProfile(User userId, Profile profile) {
        profileDao.saveProfile(userId, profile);
        return "Successfully saved profile";
    }

    @Override
    public Profile findProfileByUserId(Long userId) {
        return profileDao.findProfileByUserId(userId);
    }

    @Override
    public String deleteProfileByUserId(Long userId) {
        profileDao.deleteProfileByUserId(userId);
        return "Successfully deleted profile";
    }

}
