import hibernateexample.enitities.Contract;
import hibernateexample.enitities.Employee;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class EmployeeApp {
    public static void main(String[] args) throws IllegalAccessException {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(Employee.class);
        configuration.addAnnotatedClass(Contract.class);

        SessionFactory factory = configuration.buildSessionFactory();


    }

}
