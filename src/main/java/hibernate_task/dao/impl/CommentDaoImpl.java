package hibernate_task.dao.impl;

import hibernate_task.config.DatabaseConnection111;
import hibernate_task.dao.CommentDao;
import hibernate_task.entity.Comment;
import hibernate_task.entity.Post;
import hibernate_task.entity.User;
import jakarta.persistence.EntityManager;

import java.util.List;

public class CommentDaoImpl implements CommentDao, AutoCloseable {
    private final EntityManager em = DatabaseConnection111.getEntityManager().createEntityManager();

    @Override
    public String saveComment(Post postId, User userId, Comment comment) {
        try {
            em.getTransaction().begin();
            Post post = em.find(Post.class, postId.getId());
            User user = em.find(User.class, userId.getId());
            if (post != null && user != null) {
                comment.setPost(post);
                comment.setUsers((List<User>) user);
                em.persist(comment);
                em.getTransaction().commit();
                return "Comment saved successfully";
            } else {
                em.getTransaction().rollback();
                return "User with id " + userId.getId() + " does not exist";
            }
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Comment> findCommentsByPostId(Long postId) {
        try {
            em.getTransaction().begin();
            List<Comment> comments = em.createQuery(
                    "select c from Comment c where c.post.id = :postId",Comment.class)
                    .setParameter("postId",postId)
                    .getResultList();
            em.getTransaction().commit();
            return comments;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new RuntimeException(e);
        }
    }

    @Override
    public String updateComment(Long commentId, String newText) {
        try {
            em.getTransaction().begin();
            Comment comment = em.find(Comment.class, commentId);
            if (comment != null) {
                comment.setText(newText);
                em.getTransaction().commit();
                return "Comment updated successfully";
            }else {
                em.getTransaction().rollback();
                return "Comment with id " + commentId + " does not exist";
            }
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new RuntimeException(e);
        }
    }

    @Override
    public String deleteComment(Long commentId) {
        try {
            em.getTransaction().begin();
            Comment comment = em.find(Comment.class, commentId);
            if (comment != null) {
                em.remove(comment);
                em.getTransaction().commit();
                return "Comment deleted successfully";
            }else {
                em.getTransaction().rollback();
                return "Comment with id " + commentId + " does not exist";
            }
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() throws Exception {
        em.close();
    }
}
