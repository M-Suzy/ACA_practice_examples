package dao.impl;

import dao.EmployeeDao;
import model.Employee;
import service.DatabaseConnectionService;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class EmployeeDaoImpl implements EmployeeDao {

    public EmployeeDaoImpl() {

    }

    @Override
    public Employee findEmployeeById(int id) {
        Connection connection =
                DatabaseConnectionService.DB_INSTANCE.createConnection();

        PreparedStatement preparedStatement;
        ResultSet resultSet;
        Employee employee = null;
        try {
            preparedStatement = connection.prepareStatement(
                    "SELECT * FROM Employee WHERE id = ?"
            );

            preparedStatement.setInt(1, id);

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                employee = new Employee(
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getInt("age")
                );
            }

        } catch (SQLException throwables) {
            System.out.println("Wrong query for Employee with id=" + id);
        } finally {
            try {
                connection.close();
            } catch (SQLException throwables) {
                System.out.println("Connection cannot close");
            }
        }

        return employee;
    }


    @Override
    public void deleteById(int id) {
        Connection connection =
                DatabaseConnectionService.DB_INSTANCE.createConnection();
        try {
            Statement statement = connection.createStatement();
            String query = "DELETE FROM Employee WHERE id = " + id + ";";
            statement.execute(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void update(int id, Employee employee) {
        try (Connection connection = DatabaseConnectionService
                        .DB_INSTANCE.createConnection();
                PreparedStatement preparedStatement =
                        connection.prepareStatement(
                                "UPDATE Employee " +
                                        "SET first_name = ?, " +
                                        "last_name = ?, " +
                                        "age = ? " +
                                        "WHERE id = ?;"
                        )
        ) {
            preparedStatement.setString(1, employee.getFirstName());
            preparedStatement.setString(2, employee.getLastName());
            preparedStatement.setInt(3, employee.getAge());
            preparedStatement.setInt(4, id);

            preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void createEmployee(Employee employee) {
        Connection connection =
                DatabaseConnectionService.DB_INSTANCE.createConnection();

        String query =
                "INSERT INTO Employee (first_name, last_name, age)" +
                        " VALUES ('" +
                        employee.getFirstName() + "', '" +
                        employee.getLastName() + "', " +
                        employee.getAge() + ");";

        Statement statement = null;
        try {
            statement = connection.createStatement();
            statement.execute(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

    }

    @Override
    public Set<Employee> findAll() {
        Set<Employee> employees = null;
        try (Connection connection = DatabaseConnectionService
                .DB_INSTANCE.createConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet =
                     statement.executeQuery(
                             "SELECT * FROM Employee"
                     )
        ) {
            employees = new HashSet<>();

            Employee employee;
            while (resultSet.next()) {
                employee = new Employee(
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getInt("age")
                );

                employees.add(employee);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return employees;
    }

}
