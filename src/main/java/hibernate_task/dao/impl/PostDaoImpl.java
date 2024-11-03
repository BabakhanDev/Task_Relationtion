package hibernate_task.dao.impl;

import hibernate_task.config.DatabaseConnection111;
import hibernate_task.dao.PostDao;
import hibernate_task.entity.Post;
import hibernate_task.entity.User;
import jakarta.persistence.EntityManager;

import java.util.List;

public class PostDaoImpl implements PostDao, AutoCloseable {
    private final EntityManager em = DatabaseConnection111.getEntityManager().createEntityManager();

    @Override
    public String savePost(User userId, Post post) {
        try {
            em.getTransaction().begin();
            User user = em.find(User.class, userId);
            if (user != null) {
                post.setUser(user);
                em.persist(post);
                em.getTransaction().commit();
                return "Post saved successfully";
            } else {
                em.getTransaction().rollback();
                return "User not found";
            }
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Post> getPostByUserId(Long userId) {
        List<Post> posts = null;
        try {
            em.getTransaction().begin();
            em.createQuery("select p from Post p where p.user.id = :userId")
                    .setParameter("userId", userId)
                    .getResultList();
            em.getTransaction().commit();
            return posts;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new RuntimeException(e);
        }
    }

    @Override
    public Post searchPost(String query) {

        try {
            em.getTransaction().begin();
            Post post = em.createQuery("""
                            select p from Post  p where p.description like
                                                :query""", Post.class)
                    .setParameter("query", "%" + query + "%")
                    .getSingleResult();
            em.getTransaction().commit();
            return post;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new RuntimeException(e);
        }
    }

    @Override
    public String deletePost(Long postId) {

        try {
            em.getTransaction().begin();
            Post post = em.find(Post.class, postId);
            if (post != null) {
                em.remove(post);
                em.getTransaction().commit();
                return "Post deleted successfully";
            } else {
                em.getTransaction().rollback();
                return "Post not found";
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
