package telran.employees.test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.time.LocalDate;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import telran.employees.dto.Employee;
import telran.employees.service.Company;
import telran.employees.service.CompanyImpl;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CompanyTests {
    final static String TEST_FILE_NAME = "test.data";
    private Company company;

    @BeforeEach
    void setUp() {
        company = new CompanyImpl();
    }

    @AfterEach
    void tearDown() {
        File file = new File(TEST_FILE_NAME);
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    void testAddEmployee() {
        Employee employee = new Employee(1, "John Doe","Marketing", 1000,LocalDate.of(1997, 8, 24));
        assertTrue(company.addEmployee(employee));
    }

    @Test
    void testRemoveEmployee() {
        Employee employee = new Employee(1, "John Doe","Marketing", 1000,LocalDate.of(1997, 8, 24));
        company.addEmployee(employee);
        Employee removedEmployee = company.removeEmployee(1);
        assertNotNull(removedEmployee);
        assertEquals(employee.id(), removedEmployee.id());
    }

    @Test
    void testGetEmployee() {
        Employee employee = new Employee(1, "John Doe", "Marketing", 1000, LocalDate.of(1997, 8, 24));
        company.addEmployee(employee);
        Employee retrievedEmployee = company.getEmployee(1);
        assertNotNull(retrievedEmployee);
        assertEquals(employee.id(), retrievedEmployee.id());
    }

    @Test
    void testGetEmployees() {
        Employee employee1 = new Employee(1, "John Doe", null, 1000, LocalDate.of(1997, 8, 24));
        Employee employee2 = new Employee(2, "Jane Smith", null, 1500, LocalDate.of(1997, 8, 24));
        company.addEmployee(employee1);
        company.addEmployee(employee2);

        assertEquals(2, company.getEmployees().size());
    }

    @Test
    @Order(2)
    void testRestore() throws Exception {
        Employee employee1 = new Employee(1, "John Doe", null, 1000, LocalDate.of(1997, 8, 24));
        Employee employee2 = new Employee(2, "Jane Smith", null, 1500, LocalDate.of(1997, 8, 24));
        company.addEmployee(employee1);
        company.addEmployee(employee2);
        company.save(TEST_FILE_NAME);

        company.getEmployees().clear();
        company.restore(TEST_FILE_NAME);

        assertEquals(2, company.getEmployees().size());
    }

    @Test
    @Order(1)
    void testSave() throws Exception {
        Employee employee1 = new Employee(1, "John Doe", null, 1000, LocalDate.of(1997, 8, 24));
        Employee employee2 = new Employee(2, "Jane Smith", null, 1500, LocalDate.of(1997, 8, 24));
        company.addEmployee(employee1);
        company.addEmployee(employee2);

        company.save(TEST_FILE_NAME);

        File file = new File(TEST_FILE_NAME);
        assertTrue(file.exists());
    }
}
