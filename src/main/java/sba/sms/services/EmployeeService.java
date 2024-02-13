package sba.sms.services;

import jakarta.persistence.NamedQuery;
import jakarta.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import sba.sms.dao.EmployeeI;
import sba.sms.models.Employee;
import sba.sms.utils.HibernateUtil;

import java.util.List;

public class EmployeeService implements EmployeeI {
    SessionFactory factory = HibernateUtil.getSessionFactory();

    @Override
    public void createEmployee(Employee employee) {
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.persist(employee);
            transaction.commit();
            System.out.println("Employee created successfully!");
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Employee getEmployeeByEmail(String email) {
        Session session = factory.openSession();
        String hql = "FROM Employee e WHERE e.email = :email";
        TypedQuery<Employee> query = session.createQuery(hql, Employee.class);
        query.setParameter("email", email);
        Employee employee;
        try {
            employee = query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        session.close();
        return employee;
    }

    @Override
    public boolean validateEmployee(String email, String password) {

        Session session = factory.openSession();
        String hql = "FROM Employee e WHERE e.email = :email AND e.password = :password";
        TypedQuery<Employee> query = session.createQuery(hql, Employee.class);
        query.setParameter("email", email);
        query.setParameter("password", password);
        Employee employee = null;
        try {
            employee = query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        session.close();
        return employee != null;
    }

    @Override
    public List<Employee> getAllEmployees() {
        Session session = factory.openSession();
        String hql = "FROM Employee";
        TypedQuery<Employee> query = session.createQuery(hql, Employee.class);
        List<Employee> employeeList = null;
        try {
            employeeList = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        session.close();
        return employeeList;
    }

    @Override
    public Employee getEmployeeById(int id) {
        Session session = factory.openSession();
        String hql = "FROM Employee e WHERE e.id = :id";
        TypedQuery<Employee> query = session.createQuery(hql, Employee.class);
        query.setParameter("id", id);
        Employee employee = null;
        try {
            employee = query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        session.close();
        return employee;
    }
}
