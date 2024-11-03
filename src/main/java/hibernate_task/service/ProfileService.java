package hibernate_task.service;

import hibernate_task.entity.User;
import org.postgresql.shaded.com.ongres.stringprep.Profile;

public interface ProfileService {
    String saveProfile(User userId,Profile profile);
    Profile findProfileByUserId (Long userId);
    String deleteProfileByUserId (Long userId);
}
