package jsonxmlutils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import model.Employee;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeJsonConverter {
    public static void main(String[] args) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        List<Employee> employeeList = new ArrayList<>(3);
        /*
        employeeList.add(new Employee("name", "lastname", 18));
        employeeList.add(new Employee("name2", "lastname2", 20));
        employeeList.add(new Employee("name3", "lastname3", 30));


        try (FileWriter writer = new FileWriter("employee.json")) {

            gson.toJson(employeeList,
                    writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        */

        try (FileReader reader = new FileReader("employee.json")) {
            JsonArray jsonArray = gson.fromJson(reader, JsonArray.class);
            for (JsonElement jsonElement : jsonArray) {
                System.out.println(jsonElement.getAsJsonObject().toString());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
