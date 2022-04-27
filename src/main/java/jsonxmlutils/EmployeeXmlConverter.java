package jsonxmlutils;

import model.Employee;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class EmployeeXmlConverter {
    public static void main(String[] args) {
        JAXBContext jaxbContext = null;
        try {
            jaxbContext = JAXBContext.newInstance(Employee.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,true);
            Employee employee = new Employee("Suzy", "Melkonyan", 1);
            marshaller.marshal(employee, new File("employee.xml"));
        } catch (JAXBException e) {
            e.printStackTrace();
        }


        try {
            jaxbContext = JAXBContext.newInstance(Employee.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            Employee employee = (Employee) unmarshaller.unmarshal(new File("employee.xml"));
            System.out.println(employee.toString());
        } catch (JAXBException e) {
            e.printStackTrace();
        }

    }
}
