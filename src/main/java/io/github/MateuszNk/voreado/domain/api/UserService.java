package io.github.MateuszNk.voreado.domain.api;

import io.github.MateuszNk.voreado.domain.user.User;
import io.github.MateuszNk.voreado.domain.user.UserDao;

import java.time.LocalDateTime;

public class UserService {
    private UserDao userDao = new UserDao();

    public void register(UserRegistration userRegistration) {
        User userToSave = UserMapper.map(userRegistration);
        userDao.save(userToSave);
    }

    private static class UserMapper {
        static User map(UserRegistration userRegistration) {
            return new User (
                    userRegistration.getUsername(),
                    userRegistration.getEmail(),
                    userRegistration.getPassword(),
                    LocalDateTime.now()
            );
        }
    }
}
