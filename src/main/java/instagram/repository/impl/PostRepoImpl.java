package instagram.repository.impl;

import instagram.controller.UserController;
import instagram.entity.*;
import instagram.repository.PostRepo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Transactional
public class PostRepoImpl implements PostRepo {

    @PersistenceContext
    private final EntityManager entityManager;
    @Override
    public void createPost(Post post) {
        User user = entityManager.find(User.class, UserController.currentUser.getId());
        post.setCreatedAt(LocalDate.now());
        user.getPosts().add(post);
        post.setUser(user);
    }

    @Override
    public List<Post> findAllPosts() {
        return entityManager.createQuery("select p from Post p", Post.class).getResultList();
    }

    @Override
    public Post findPostById(Long id) {
        return entityManager.find(Post.class, id);
    }

    @Override
    public void updatePost(Long id, Post newPost) {
        Post post = entityManager.find(Post.class, id);
        post.setTitle(newPost.getTitle());
        post.setDescription(newPost.getDescription());
    }

    @Override
    public void deletePost(Long id) {
        Post post = entityManager.find(Post.class, id);
//        for (Image image : post.getImages()) {
//            post.getImages().remove(image);
//        }


        entityManager.createQuery("delete from Image i where i.post.id = :parId")
                        .setParameter("parId", id).executeUpdate();
        UserController.currentUser.getPosts().remove(post);
        entityManager.createQuery("delete from Post p where p.id = :parId")
                .setParameter("parId", id)
                .executeUpdate();
    }
}
