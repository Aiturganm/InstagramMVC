package instagram.repository;

import instagram.entity.User;

import java.util.List;

public interface UserRepo {
    List<User> findAll();
    String save(User user);
    User login(User user);
    User getUserById(Long id);
    User findUserByName(String name);
    void updateUser(Long userId, User newUser);

}
