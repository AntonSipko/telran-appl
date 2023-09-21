package telran.employees.service;

import java.util.*;

import telran.employees.dto.Employee;

public class CompanyImpl implements Company {
	HashMap<Long,Employee>employees=new HashMap<>();

	@Override
	public boolean addEmployee(Employee empl) {
		boolean res=false;
		if(!employees.containsKey(empl.id())) {
			employees.put(empl.id(), empl);
			res=true;
			
		}
		return res;
		
		
	}

	@Override
	public Employee removeEmployee(long id) {
		Employee emplToRemove=employees.getOrDefault(id, null);
		if(emplToRemove!=null) {
			employees.remove(id);
		}
		
		return emplToRemove;
	}

	@Override
	public Employee getEmployee(long id) {
		Employee empl=employees.getOrDefault(id, null);
		return empl;
	}

	@Override
	public List<Employee> getEmployees() {
		 List<Employee>emlList= new ArrayList<>(employees.values());
		return emlList;
	}

}
