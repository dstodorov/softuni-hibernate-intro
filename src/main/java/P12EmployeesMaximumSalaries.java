import javax.persistence.EntityManager;
import javax.persistence.Persistence;
public class P12EmployeesMaximumSalaries {
    public static void main(String[] args) {
        EntityManager entityManager = Persistence.createEntityManagerFactory("soft_uni").createEntityManager();

        entityManager
                .createQuery("SELECT e.department.name, MAX(e.salary) FROM Employee e GROUP BY e.department.name HAVING MAX(e.salary) NOT BETWEEN 30000 AND 70000", Object[].class)
                .getResultList()
                .forEach( o ->
                    System.out.printf("%s %s%n", o[0], o[1])
                );
    }
}
