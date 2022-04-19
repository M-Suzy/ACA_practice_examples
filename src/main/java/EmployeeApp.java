import dao.EmployeeDao;
import dao.impl.EmployeeDaoImpl;
import model.Employee;

public class EmployeeApp {
    public static void main(String[] args) {
        EmployeeDao employeeDao = new EmployeeDaoImpl();


        for (int i = 0; i < 10; i++) {
            employeeDao.createEmployee(
                    new Employee(
                            "firstname" + i,
                            "lastname" + i,
                            i
                    )
            );
        }

        System.out.println(
                employeeDao.findEmployeeById(32)
        );

        employeeDao.update(32, new Employee(
                "newfirstname",
                "newlastname",
                50
        ));

        System.out.println(
                employeeDao.findEmployeeById(32)
        );
    }
}
