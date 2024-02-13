package sba.sms.dao;

import sba.sms.models.Employee;

import java.util.List;

public interface EmployeeI {

    void createEmployee(Employee employee);

    Employee getEmployeeByEmail(String email);

    boolean validateEmployee(String email, String password);

    List<Employee> getAllEmployees();

    Employee getEmployeeById(int empId);

}
