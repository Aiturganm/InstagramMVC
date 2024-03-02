package instagram.repository.impl;

import instagram.entity.*;
import instagram.repository.UserRepo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Stack;

@Repository
@RequiredArgsConstructor
@Transactional
public class UserRepoImpl implements UserRepo {
    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public List<User> findAll() {
        return entityManager
                .createQuery("select u from User u", User.class)
                .getResultList();
    }

    @Override
    public String save(User user) {
        List<User> all = findAll();
        int doesntExists = 0;
        for (User user1 : all) {
            if (!user.getEmail().equals(user1.getEmail())) {
                if (!user.getUserName().equals(user1.getUserName())) {
                    if (user.getPhoneNumber().startsWith("+996")) {
                        doesntExists = 0;
                    } else {
                        throw new RuntimeException();
                    }
                } else {
                    doesntExists += 1;
                    return "name";
                }
            } else {
                doesntExists += 1;
                return "email";
            }
        }
        if (doesntExists == 0) {
            entityManager.persist(user);
        }
        return "saved";
    }


    @Override
    public User login(User user) {
        for (User user1 : findAll()) {
            if (user1.getUserName().equals(user.getUserName()) && user1.getEmail().equals(user.getEmail()) ) {
                return user1;
            }
        }
        return null;
    }



    @Override
    public User getUserById(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public User findUserByName(String name) {
        return entityManager.find(User.class, name);
    }

    @Override
    public void updateUser(Long userId, User newUser) {
        User user = entityManager.find(User.class, userId);
        user.setUserName(newUser.getUserName());
        user.setEmail(newUser.getEmail());
        user.setPhoneNumber(newUser.getPhoneNumber());
        user.setPassword(newUser.getPassword());
        user.setImage(newUser.getImage());
    }
}
