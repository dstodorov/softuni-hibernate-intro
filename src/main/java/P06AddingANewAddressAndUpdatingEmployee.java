import entities.Address;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

public class P06AddingANewAddressAndUpdatingEmployee {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();

        Address address = new Address();
        address.setText("Vitoshka 15");

        entityManager.persist(address);

        Scanner scanner = new Scanner(System.in);
        String lastName = scanner.nextLine();

        int countOfUpdatedRecords = entityManager.createQuery("UPDATE Employee SET address = :employee_address WHERE lastName = :last_name")
                .setParameter("employee_address", address)
                .setParameter("last_name", lastName)
                .executeUpdate();

        System.out.println(countOfUpdatedRecords);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
