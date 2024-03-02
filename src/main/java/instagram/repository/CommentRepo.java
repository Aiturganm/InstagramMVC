package instagram.repository;

import instagram.entity.Comment;
import instagram.entity.Post;
import instagram.entity.User;

import java.util.List;

public interface CommentRepo {
    void saveComment(Comment comment, User user, Long postId);

    List<Comment> getComments(Long postId);
}
