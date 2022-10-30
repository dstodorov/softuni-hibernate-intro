import entities.Project;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class P09FindLatest10Projects {
    public static void main(String[] args) {
        EntityManager entityManager = Persistence.createEntityManagerFactory("soft_uni").createEntityManager();

        entityManager.createQuery("SELECT p FROM Project p ORDER BY p.name", Project.class)
                .setMaxResults(10)
                .getResultList()
                .forEach(p -> System.out.printf("Project name: %s%n " +
                                "\tProject description: %s%n" +
                                "\tProject Start Date: %s%n" +
                                "\tProject End Date: %s%n",
                        p.getName(), p.getDescription(), p.getStartDate().toString(), p.getEndDate() == null ? "null" : p.getEndDate().toString()));
    }
}
