package instagram.repository.impl;

import instagram.entity.Follower;
import instagram.entity.User;
import instagram.repository.FollowerRepo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Transactional
public class FollowerRepoImpl implements FollowerRepo {
    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public List<User> search(String userName) {
        List<User> userList = new ArrayList<>();
        userList = entityManager.createQuery("select u from User u where u.userName ilike (:parName)", User.class)
                .setParameter("parName", userName).getResultList();
        return userList;
    }

    @Override
    public boolean subscribe(Long userId, Long subscribeUserId) {
        User user = entityManager.find(User.class, userId);
        Follower follower = user.getFollower();
        List<Long> subscribers = follower.getSubscribers();


        User me = entityManager.find(User.class, subscribeUserId);
        Follower myFollower = me.getFollower();
        List<Long> subscriptions = myFollower.getSubscriptions();

        boolean isSubscribe = false;

        for(Long id : subscribers){
            if(id.equals(subscribeUserId)){
                subscribers.remove(subscribeUserId);
                subscriptions.remove(userId);
                isSubscribe = true;
                break;
            }
        }
        if (!isSubscribe) {
            subscribers.add(subscribeUserId);
            subscriptions.add(userId);
        }
        return isSubscribe;
    }

    @Override
    public List<Long> getAllSubscribersByUserId(Long id) {
        User user = entityManager.find(User.class, id);
        Follower follower = user.getFollower();
        return follower.getSubscribers();
    }

    @Override
    public List<Long> getAllSubscriptionsByUserId(Long id) {
        User user = entityManager.find(User.class, id);
        Follower follower = user.getFollower();
        return follower.getSubscriptions();
    }
}
