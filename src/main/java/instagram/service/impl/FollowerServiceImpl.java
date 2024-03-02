package instagram.service.impl;

import instagram.entity.User;
import instagram.repository.FollowerRepo;
import instagram.service.FollowerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FollowerServiceImpl implements FollowerService {

    private final FollowerRepo followerRepo;
    @Override
    public List<User> search(String userName) {
        return followerRepo.search("%"+userName+"%");
    }

    @Override
    public boolean subscribe(Long userId, Long subscribeUserId) {
        return followerRepo.subscribe(userId, subscribeUserId);
    }

    @Override
    public List<Long> getAllSubscribersByUserId(Long id) {
        return followerRepo.getAllSubscribersByUserId(id);
    }

    @Override
    public List<Long> getAllSubscriptionsByUserId(Long id) {
        return followerRepo.getAllSubscriptionsByUserId(id);
    }
}
