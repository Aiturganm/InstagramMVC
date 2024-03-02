package instagram.repository.impl;

import instagram.entity.User;
import instagram.entity.UserInfo;
import instagram.repository.UserInfoRepo;
import instagram.service.UserInfoService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
@Transactional
public class UserInfoImpl implements UserInfoRepo {

    @PersistenceContext
    private final EntityManager entityManager;

    public static UserInfo currentUserInfo = new UserInfo();

    @Override
    public UserInfo findUserInfoByUserId(Long userId) {
        User user = entityManager.find(User.class, userId);
        if(user.getId().equals(user.getUserInfo().getId())){
            return user.getUserInfo();
        }else return null;
    }

    @Override
    public String update(Long id, UserInfo newUserInfo) {
        UserInfo userInfo = entityManager.find(UserInfo.class, id);
        userInfo.setFullName(newUserInfo.getFullName());
        userInfo.setBiography(newUserInfo.getBiography());
        userInfo.setGender(newUserInfo.getGender());
        userInfo.setImage(newUserInfo.getImage());
        return "updated";
    }

    @Override
    public void changeImage(String urlImg, Long id) {
        UserInfo userInfo = entityManager.find(UserInfo.class, id);
        userInfo.setImage(urlImg);
    }

    @Override
    public void deleteImage(Long id) {
        UserInfo userInfo = entityManager.find(UserInfo.class, id);
        userInfo.setImage("https://i.pinimg.com/736x/0d/64/98/0d64989794b1a4c9d89bff571d3d5842.jpg");
    }
}
