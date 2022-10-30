import entities.Town;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.stream.Collectors;

public class ChangeCasing {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = factory.createEntityManager();

        entityManager.getTransaction().begin();

        List<Town> from_town = entityManager.createQuery("FROM Town", Town.class).getResultList()
                .stream()
                .filter(t -> t.getName().length() > 5)
                .collect(Collectors.toList());

        from_town.forEach(t ->
                t.setName(t.getName().toUpperCase())
        );

        from_town.forEach(entityManager::persist);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
