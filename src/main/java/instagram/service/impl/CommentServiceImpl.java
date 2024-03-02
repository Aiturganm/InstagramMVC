package instagram.service.impl;

import instagram.entity.Comment;
import instagram.entity.User;
import instagram.repository.CommentRepo;
import instagram.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepo commentRepo;

    @Override
    public void saveComment(Comment comment, User user, Long postId) {
        commentRepo.saveComment(comment, user, postId);
    }

    @Override
    public List<Comment> getComments(Long postId) {
        return commentRepo.getComments(postId);
    }
}
