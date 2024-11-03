package hibernate_task.service.impl;

import hibernate_task.dao.CommentDao;
import hibernate_task.dao.impl.CommentDaoImpl;
import hibernate_task.entity.Comment;
import hibernate_task.entity.Post;
import hibernate_task.entity.User;
import hibernate_task.service.CommentService;

import java.util.List;

public class CommentServiceImpl implements CommentService {
    private final CommentDao commentDao = new CommentDaoImpl();
    @Override
    public String saveComment(Post postId, User userId, Comment comment) {
        commentDao.saveComment(postId, userId, comment);
        return "Successfully saved comment";
    }

    @Override
    public List<Comment> findCommentsByPostId(Long postId) {
        return commentDao.findCommentsByPostId(postId);
    }

    @Override
    public String updateComment(Long commentId, String newText) {
        commentDao.updateComment(commentId, newText);
        return "Successfully updated comment";
    }

    @Override
    public String deleteComment(Long commentId) {
        commentDao.deleteComment(commentId);
        return "Successfully deleted comment";
    }

}
