import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import java.util.Scanner;

public class ContainsEmployee {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();

        Scanner scanner = new Scanner(System.in);

        String[] fullName = scanner.nextLine().split("\\s+");
        String firstName = fullName[0];
        String lastName = fullName[1];

        try {
            entityManager.createQuery("FROM Employee WHERE firstName = :first_name AND lastName = :last_name", Employee.class)
                    .setParameter("first_name", firstName)
                    .setParameter("last_name", lastName)
                    .getSingleResult();
            System.out.println("Yes");
        } catch (NoResultException e) {
            System.out.println("No");
        }

        entityManager.getTransaction().commit();
        entityManager.close();

    }
}
