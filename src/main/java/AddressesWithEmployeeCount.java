import entities.Address;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class AddressesWithEmployeeCount {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();

        entityManager.createQuery("select a from Address a order by a.employees.size DESC", Address.class)
                .setMaxResults(10)
                .getResultList()
                .forEach(System.out::println);
        entityManager.close();
    }
}
