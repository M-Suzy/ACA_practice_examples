package hibernateexample.repository;

import hibernateexample.enitities.Employee;
import hibernateexample.enitities.EmployeeProject;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.HashSet;
import java.util.Set;

public class EmployeeRepositoryImpl implements EmployeeRepository{
    private SessionFactory sessionFactory;

    public EmployeeRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Employee findById(Long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Employee employee= session.find(Employee.class, id);

        transaction.commit();
        session.close();

        return employee;
    }

    @Override
    public Employee findBySSN(Employee employee) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Employee emp = null;
        try {
            emp = (Employee) session.createQuery("select e FROM Employee e WHERE ssn = :SSN")
                    .setParameter("SSN", employee.getSSN()).getSingleResult();
        } catch (Exception e){
            return null;
        }

        transaction.commit();
        session.close();

        return emp;
    }

    @Override
    public Set<Employee> findAll() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Query<Employee> query =
                session.createQuery(
                        "SELECT e FROM Employee e",
                        Employee.class);
        Set<Employee> employees = new HashSet<>(query.getResultList());

        transaction.commit();
        session.close();

        return employees;
    }

    @Override
    public Employee save(Employee employee) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.persist(employee);

        transaction.commit();
        session.close();

        return employee;
    }

    @Override
    public Employee update(Employee employee) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.merge(employee);

        transaction.commit();
        session.close();

        return employee;
    }

    @Override
    public void deleteById(Long id) {

            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();

            session.createQuery("DELETE FROM Employee WHERE id = :ID")
                    .setParameter("ID", id)
                    .executeUpdate();

            transaction.commit();
            session.close();
    }


    //TODO Warning write a normal code
    @Override
    public void addTeamBuildingInfo(String info, long projId, long empId) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        EmployeeProject employeeProject =
                session.find(EmployeeProject.class, new EmployeeProject.ProjectEmployeeId(projId, empId));
        if(employeeProject!=null){
            employeeProject.setTeamBuilding(info);
            session.merge(employeeProject);
        }
        transaction.commit();
        session.close();

    }
}
