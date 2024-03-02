package instagram.repository;

import instagram.entity.Post;

public interface LikeRepo {
    boolean like(Long postId, Long userId);
}
