package hibernate_task.service.impl;

import hibernate_task.dao.PostDao;
import hibernate_task.dao.impl.PostDaoImpl;
import hibernate_task.entity.Post;
import hibernate_task.entity.User;
import hibernate_task.service.PostService;

import java.util.List;

public class PostServiceImpl implements PostService  {
    private final PostDao postDao = new PostDaoImpl();
    @Override
    public String savePost(User userId, Post post) {
        postDao.savePost(userId, post);
        return "Successfully saved post";
    }

    @Override
    public List<Post> getPostByUserId(Long userId) {
        return postDao.getPostByUserId(userId);
    }

    @Override
    public Post searchPost(String query) {
        return postDao.searchPost(query);
    }

    @Override
    public String deletePost(Long postId) {
        postDao.deletePost(postId);
        return "Successfully deleted post";
    }
}
