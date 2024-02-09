package sba.sms.services;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Helper;
import lombok.extern.java.Log;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import sba.sms.dao.StudentI;
import sba.sms.models.Course;
import sba.sms.models.Student;
import sba.sms.utils.HibernateUtil;

import java.security.spec.ECField;
import java.sql.SQLException;
import java.util.*;

/**
 * StudentService is a concrete class. This class implements the
 * StudentI interface, overrides all abstract service methods and
 * provides implementation for each method. Lombok @Log used to
 * generate a logger file.
 */

public class StudentService implements StudentI {

    //  SessionFactory factory = new Configuration().configure().buildSessionFactory();
    SessionFactory factory = HibernateUtil.getSessionFactory();
    Session session = null;

    @Override
    public List<Student> getAllStudents() {
        session = factory.openSession();
        String hql = "From Student";
        TypedQuery<Student> query = session.createQuery(hql, Student.class);
        List<Student> students;
        try {
            students = query.getResultList();
        } catch (Exception e) {
            return null;
        }
        session.close();
        return students;
    }

    @Override
    public void createStudent(Student student) {
        session = factory.openSession();
        Transaction t = session.beginTransaction();
        try {
            session.persist(student);
            t.commit();
        } catch (Exception e) {
            if (t != null) {
                t.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public Student getStudentByEmail(String email) {
        session = factory.openSession();
        TypedQuery<Student> query = session.createQuery("From Student s WHERE s.email = :email");
        query.setParameter("email", email);
        Student student;
        try {
            student = query.getSingleResult();
        } catch (Exception e) {
            return null;
        }

        session.close();
        return student;
    }

    @Override
    public boolean validateStudent(String email, String password) {

        session = factory.openSession();
        String hql = "From Student s where s.email = :email AND s.password = :password";
        TypedQuery<Student> query = session.createQuery(hql, Student.class);
        query.setParameter("email", email);
        query.setParameter("password", password);
        Student student = null;
        try {
            student = query.getSingleResult();
        } catch (Exception e) {
            return false;
        }

        session.close();

        return student != null;
    }

    @Override
    public void registerStudentToCourse(String email, int courseId) {
        session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        String hql = "FROM Student s where s.email = :email";
        TypedQuery<Student> query = session.createQuery(hql, Student.class);
        query.setParameter("email", email);
        Student student = query.getSingleResult();

        String hql2 = "From Course c where c.id = :courseId";
        TypedQuery<Course> query1 = session.createQuery(hql2, Course.class);
        query1.setParameter("courseId", courseId);
        Course course = query1.getSingleResult();
        Set<Course> courseSet = new HashSet<>();
        courseSet.add(course);
        student.setCourses(courseSet);

        session.merge(student);
        transaction.commit();
        session.close();
    }

    @Override
    public List<Course> getStudentCourses(String email) {
        session = factory.openSession();
        String hql = "Select s From Student s where s.email = :email";
        TypedQuery<Student> query = session.createQuery(hql, Student.class);
        query.setParameter("email", email);
        Student student = query.getSingleResult();
        Set<Course> courses = student.getCourses();
        return new ArrayList<>(courses);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentService that = (StudentService) o;
        session = factory.openSession();
        return Objects.equals(factory, that.factory) && Objects.equals(session, that.session);
    }

    @Override
    public int hashCode() {
        session = factory.openSession();
        return Objects.hash(factory, session);
    }

    @Override
    public String toString() {
        session = factory.openSession();
        return "StudentService{" +
                "factory=" + factory +
                ", session=" + session +
                '}';
    }
}
