import hibernateexample.enitities.Contract;
import hibernateexample.enitities.Department;
import hibernateexample.enitities.Employee;
import hibernateexample.enitities.Project;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class EmployeeApp {
    public static void main(String[] args) throws IllegalAccessException {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(Employee.class);
        configuration.addAnnotatedClass(Contract.class);
        configuration.addAnnotatedClass(Department.class);
        configuration.addAnnotatedClass(Project.class);

        SessionFactory factory = configuration.buildSessionFactory();


    }

}
