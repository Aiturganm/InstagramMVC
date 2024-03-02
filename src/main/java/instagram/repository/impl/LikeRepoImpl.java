package instagram.repository.impl;

import instagram.entity.Like;
import instagram.entity.Post;
import instagram.entity.User;
import instagram.repository.LikeRepo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@Transactional
@RequiredArgsConstructor
public class LikeRepoImpl implements LikeRepo {

    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public boolean like(Long postId, Long userId) {
        Post post = entityManager.find(Post.class, postId);
        List<Like> likes = post.getLikes();

        User user = entityManager.find(User.class, userId);
        Like like = user.getLike();


        boolean isLike = false;

        for (Like postLike : likes) {
            if (postLike.getId().equals(like.getId())) {
                likes.remove(postLike);
                isLike = true;
                break;
            }
        }

        if(!isLike){
            likes.add(like);
        }

        return isLike;
    }
}
