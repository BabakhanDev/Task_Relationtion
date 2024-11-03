package hibernate_task;

import hibernate_task.config.DatabaseConnection111;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class App {
    public static void main(String[] args) {


        DatabaseConnection111 connection = new DatabaseConnection111();
        EntityManagerFactory em = DatabaseConnection111.getEntityManager();
    }
}
