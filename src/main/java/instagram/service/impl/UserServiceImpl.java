package instagram.service.impl;

import instagram.entity.*;
import instagram.repository.UserRepo;
import instagram.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;

    @Override
    public List<User> findAll() {
        return userRepo.findAll();
    }

    @Override
    public String save(User user) {
        Follower follower = new Follower();
        UserInfo userInfo = new UserInfo();
        Like like = new Like();
        like.setIsLike(false);
        userInfo.setImage("https://i.pinimg.com/736x/0d/64/98/0d64989794b1a4c9d89bff571d3d5842.jpg");
        user.setFollower(follower);
        follower.setUser(user);
        user.setUserInfo(userInfo);
        user.setLike(like);
        like.setUser(user);
        String save = null;
        try {
            save = userRepo.save(user);
        } catch (Exception e) {
            return "number";
        }
        return save;
    }

    @Override
    public User login(User user) {
        try {
            return userRepo.login(user);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public User getUserById(Long id) {
        if (!findAll().isEmpty()) {
            return userRepo.getUserById(id);
        }
        else return null;
    }

    @Override
    public User findUserByName(String name) {
        if(!findAll().isEmpty()) {
            return userRepo.findUserByName(name);
        }else return null;
    }

    @Override
    public void updateUser(Long userId, User newUser) {
        userRepo.updateUser(userId, newUser);
    }
}
