package instagram.repository.impl;

import instagram.controller.UserController;
import instagram.entity.*;
import instagram.repository.CommentRepo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Transactional
public class CommentRepoImpl implements CommentRepo {
    @PersistenceContext
    private final EntityManager entityManager;
    @Override
    public void saveComment(Comment comment, User user, Long postId) {
        User findedUser = entityManager.find(User.class, user.getId());

        Post post = entityManager.find(Post.class, postId);

        comment.setCreatedAt(LocalDate.now());
        post.getComments().add(comment);
        comment.setPost(post);
        comment.setUser(user);
        findedUser.getComments().add(comment);
    }

    @Override
    public List<Comment> getComments(Long postId) {
        String query = "select c from Comment c where post.id = (:parId)";
        return entityManager.createQuery(query, Comment.class).setParameter("parId", postId).getResultList();
    }
}
