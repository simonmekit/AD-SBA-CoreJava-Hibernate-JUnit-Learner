package sba.sms.services;

import jakarta.persistence.TypedQuery;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import sba.sms.dao.CourseI;
import sba.sms.models.Course;
import sba.sms.utils.HibernateUtil;

import java.security.spec.ECField;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 * CourseService is a concrete class. This class implements the
 * CourseI interface, overrides all abstract service methods and
 * provides implementation for each method.
 */
public class CourseService implements CourseI {

    SessionFactory factory = HibernateUtil.getSessionFactory();

    @Override
    public void createCourse(Course course) {
        //SessionFactory factory = new Configuration().configure().buildSessionFactory();

        Session session = factory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.persist(course);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public Course getCourseById(int courseId) {
        Session session = factory.openSession();
        // Transaction transaction = session.beginTransaction();
        Course course = null;

        String sql = "From Course c where c.id = :id";
        TypedQuery<Course> query = session.createQuery(sql, Course.class);
        query.setParameter("id", courseId);
        try {
            course = query.getSingleResult();
        } catch (Exception e) {
            return null;
        }


        return course;
    }

    @Override
    public List<Course> getAllCourses() {
        Session session = factory.openSession();
        TypedQuery<Course> query = session.createQuery("FROM Course ", Course.class);
        List<Course> courses;
        try {
            courses = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return courses;
    }
}
