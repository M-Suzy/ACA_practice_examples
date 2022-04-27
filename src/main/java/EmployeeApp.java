import annotations.Column;
import annotations.Table;
import model.User;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class EmployeeApp {
    public static void main(String[] args) throws IllegalAccessException {
/*        EmployeeDao employeeDao = new EmployeeDaoImpl();


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
        );*/
        User valod = new User("Valod", "12345");

        System.out.println(generateInsertQuery(valod));

        User hakob = new User("Hakob", "54321");

        System.out.println(generateInsertQuery(hakob));
    }

    private static String generateInsertQuery(User user) throws IllegalAccessException {
        Class<? extends User> cls = user.getClass();

        Table tableAnnotation = cls.getDeclaredAnnotation(Table.class);

        if (tableAnnotation == null) {
            throw new IllegalStateException(
                    "Provided object is not an entity"
            );
        }

        String tableName = tableAnnotation.name();

        Field[] declaredFields = cls.getDeclaredFields();
        List<Field> annotatedFields = new ArrayList<>();
        for (Field declaredField : declaredFields) {
            if (declaredField.getDeclaredAnnotation(Column.class) != null) {
                annotatedFields.add(declaredField);
            }
        }

        StringBuilder columns = new StringBuilder();
        StringBuilder values = new StringBuilder();
        for (Field annotatedField : annotatedFields) {
            columns.append(annotatedField
                    .getDeclaredAnnotation(Column.class)
                    .name()).append(",");
            annotatedField.setAccessible(true);
            values.append(annotatedField.get(user)).append(",");
        }

        return "INSERT INTO " + tableName + "(" +
                columns.substring(0, columns.length() - 1) + ") VALUES(" +
                values.substring(0, values.length() - 1) + ");";
    }

}
