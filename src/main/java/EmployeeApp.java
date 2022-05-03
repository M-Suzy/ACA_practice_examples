import hibernateexample.enitities.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class EmployeeApp {
    public static void main(String[] args) throws IllegalAccessException {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(Employee.class);
        configuration.addAnnotatedClass(Contract.class);
        configuration.addAnnotatedClass(Department.class);
        configuration.addAnnotatedClass(Project.class);
        configuration.addAnnotatedClass(EmployeeProject.class);


        SessionFactory factory = configuration.buildSessionFactory();


        Employee employee = new Employee();
        employee.setAge(18);
        employee.setFirstName("Absahsyf");
        employee.setLastName("Arfhdjrtht");
        employee.setSSN("679193848q38");

        Department department = new Department("IT");
        department.addEmployee(employee);
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(department);

        transaction.commit();
        session.close();

        department.setName("Accounting");
        Session session1 = factory.openSession();
        Transaction transaction1 = session1.beginTransaction();
        session1.merge(department);

        transaction1.commit();
        session1.close();


       // Project project = new Project("Apollo");


        //EmployeeProjectService employeeProjectService =
        //        new EmployeeProjectService(new EmployeeRepositoryImpl(factory),
        //                new ProjectRepositoryImpl(factory));//
        // employeeProjectService.saveEmployeeProjects(employee, project, "Dinner");

    }

}
