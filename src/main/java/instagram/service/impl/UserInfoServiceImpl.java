package instagram.service.impl;

import instagram.entity.User;
import instagram.entity.UserInfo;
import instagram.repository.UserInfoRepo;
import instagram.service.UserInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserInfoServiceImpl implements UserInfoService {

    private final UserInfoRepo userInfoRepo;
    @Override
    public UserInfo findUserInfoByUserId(Long userId) {
        return userInfoRepo.findUserInfoByUserId(userId);
    }

    @Override
    public String update(Long id, UserInfo newUserInfo) {
        if(findUserInfoByUserId(id)!=null) {
            return userInfoRepo.update(id, newUserInfo);
        }else return null;
    }

    @Override
    public void changeImage(String urlImg, Long id) {
        userInfoRepo.changeImage(urlImg, id);
    }

    @Override
    public void deleteImage(Long id) {
        userInfoRepo.deleteImage(id);
    }
}
