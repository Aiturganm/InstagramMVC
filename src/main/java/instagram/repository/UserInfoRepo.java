package instagram.repository;

import instagram.entity.User;
import instagram.entity.UserInfo;

public interface UserInfoRepo {
    UserInfo findUserInfoByUserId(Long userId);
    String update(Long id, UserInfo newUserInfo);
    void changeImage(String urlImg, Long id);
    void deleteImage(Long id);

}
