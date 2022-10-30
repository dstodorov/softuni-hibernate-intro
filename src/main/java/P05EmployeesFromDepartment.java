import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class P05EmployeesFromDepartment {

    private static final String DEPARTMENT_NAME = "Research and Development";
    private static final String EMPLOYEE_INFO_FORMAT = "%s %s from %s - $%.2f%n";
    private static final String EMPLOYEES_FROM_DEPARTMENT_QUERY =  "FROM Employee e WHERE e.department.name = :department_name ORDER BY e.salary ASC, e.id ASC";

    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();

        entityManager.createQuery(EMPLOYEES_FROM_DEPARTMENT_QUERY, Employee.class)
                .setParameter("department_name", DEPARTMENT_NAME)
                .getResultList()
                .forEach(e -> System.out.printf(EMPLOYEE_INFO_FORMAT, e.getFirstName(), e.getLastName(), e.getDepartment(), e.getSalary()));

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
