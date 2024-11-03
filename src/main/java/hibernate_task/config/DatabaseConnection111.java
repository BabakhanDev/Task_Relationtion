package hibernate_task.config;

import hibernate_task.entity.Comment;
import hibernate_task.entity.Post;
import hibernate_task.entity.Profile;
import hibernate_task.entity.User;
import jakarta.persistence.EntityManagerFactory;
import java15.entity.Person;
import java15.entity.SocialMedia;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.util.Properties;

public class DatabaseConnection111 {
    public static EntityManagerFactory getEntityManager() {
        Properties properties = new Properties();
        properties.put(Environment.JAKARTA_JDBC_URL, "jdbc:postgresql://localhost:5432/java15");
        properties.put(Environment.JAKARTA_JDBC_USER, "postgres");
        properties.put(Environment.JAKARTA_JDBC_PASSWORD, "postgres");
        properties.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQLDialect");
        properties.put(Environment.HBM2DDL_AUTO, "update");
        properties.put(Environment.SHOW_SQL, true);
        properties.put(Environment.FORMAT_SQL, "true");

        Configuration configuration = new Configuration();
        configuration.setProperties(properties);
        configuration.addAnnotatedClass(Post.class);
        configuration.addAnnotatedClass(Comment.class);
        configuration.addAnnotatedClass(User.class);
        configuration.addAnnotatedClass(Profile.class);
        return configuration.buildSessionFactory().unwrap(SessionFactory.class);
    }
}
