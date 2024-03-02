package instagram.service.impl;

import instagram.entity.Comment;
import instagram.entity.Image;
import instagram.entity.Like;
import instagram.entity.Post;
import instagram.repository.PostRepo;
import instagram.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepo postRepo;
    @Override
    public void createPost(Image image, Post post) {
        List<Image> images = new ArrayList<>();
        List<Comment> comments = new ArrayList<>();
        post.setComments(comments);
        image.setPost(post);
        post.setImage(image);
        postRepo.createPost(post);
    }

    @Override
    public List<Post> findAllPosts() {
        return postRepo.findAllPosts();
    }

    @Override
    public Post findPostById(Long id) {
        return postRepo.findPostById(id);
    }

    @Override
    public void updatePost(Long id, Post newPost) {
        postRepo.updatePost(id, newPost);
    }

    @Override
    public void deletePost(Long id) {
        postRepo.deletePost(id);
    }
}
