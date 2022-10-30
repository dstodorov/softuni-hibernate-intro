import entities.Employee;
import entities.Project;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Comparator;
import java.util.Scanner;

public class GetEmployeeWithProject {
    public static void main(String[] args) {
        EntityManager entityManager = Persistence.createEntityManagerFactory("soft_uni").createEntityManager();

        Scanner scanner = new Scanner(System.in);

        int employeeId = Integer.parseInt(scanner.nextLine());

        entityManager.getTransaction().begin();
        Employee employee = entityManager.createQuery("SELECT e FROM Employee e WHERE e.id = :employee_id", Employee.class)
                .setParameter("employee_id", employeeId)
                .getSingleResult();

        System.out.printf("%s %s - %s%n", employee.getFirstName(), employee.getLastName(), employee.getJobTitle());
        employee.getProjects()
                .stream()
                .sorted(Comparator.comparing(Project::getName))
                .forEach(p -> System.out.println("\t" + p.getName()));


    }
}
