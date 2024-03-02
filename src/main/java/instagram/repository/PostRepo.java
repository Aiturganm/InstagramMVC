package instagram.repository;

import instagram.entity.Post;

import java.util.List;
import java.util.Stack;

public interface PostRepo {
    void createPost(Post post);
    List<Post> findAllPosts();
    Post findPostById(Long id);
    void updatePost(Long id, Post newPost);
    void deletePost(Long id);
}
