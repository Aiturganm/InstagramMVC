package instagram.service.impl;

import instagram.repository.LikeRepo;
import instagram.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService {
    private final LikeRepo likeRepo;

    @Override
    public boolean like(Long postId, Long userId) {
        return likeRepo.like(postId, userId);
    }
}
