package hibernate_task.service;

import hibernate_task.entity.Comment;
import hibernate_task.entity.Post;
import hibernate_task.entity.User;

import java.util.List;

public interface CommentService {
    String saveComment(Post postId, User userId, Comment comment);
    List<Comment> findCommentsByPostId(Long postId);
    String updateComment(Long commentId,String newText);
    String deleteComment(Long commentId);
}
