package hibernate_task.service;

import hibernate_task.entity.Post;
import hibernate_task.entity.User;

import java.util.List;

public interface PostService {
    String savePost(User userId, Post post);
   List<Post> getPostByUserId(Long userId);
   Post searchPost (String query);
   String deletePost(Long postId);
}
