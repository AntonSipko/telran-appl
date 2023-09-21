package telran.employees.service;
import java.io.*;
import java.util.List;

import telran.employees.dto.Employee;

public interface Company {
	boolean addEmployee(Employee empl);
	Employee removeEmployee(long id);
	Employee getEmployee(long id);
	List<Employee>getEmployees();
	default void restore(String dataFile) throws Exception {
		try(ObjectInputStream input=new ObjectInputStream(new FileInputStream(dataFile))){
			List<Employee>employees=(List<Employee>) input.readObject();
			getEmployees().clear();
			getEmployees().addAll(employees);
			
		}catch(FileNotFoundException e) {
			
		} catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("Error restoring employee data", e);
        }
		
	}
	default void save(String dataFile) throws Exception {
		try(ObjectOutputStream output=new ObjectOutputStream(new FileOutputStream(dataFile))){
			List<Employee> employees=getEmployees();
			output.writeObject(employees);
			
		}catch(FileNotFoundException e) {
			
		} catch (IOException e) {
            throw new RuntimeException("Error saving employee data", e);
        }
		
		
	}
	
	

}
