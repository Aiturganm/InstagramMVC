package instagram.repository;
import instagram.entity.User;
import java.util.List;

public interface FollowerRepo {
    List<User> search(String userName);
    boolean subscribe(Long userId, Long subscribeUserId);
    List<Long> getAllSubscribersByUserId(Long id);
    List<Long> getAllSubscriptionsByUserId(Long id);
}
