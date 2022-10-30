import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EmployeesWithSalaryOver50000 {

    private static final double MIN_SALARY = 50000;

    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();

        entityManager.createQuery("FROM Employee WHERE salary > :min_salary", Employee.class)
                .setParameter("min_salary", MIN_SALARY)
                .getResultList()
                .stream()
                .map(Employee::getFirstName)
                .forEach(System.out::println);
    }
}
