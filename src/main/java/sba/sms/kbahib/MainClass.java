package sba.sms.kbahib;

import jakarta.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import sba.sms.models.Person;

import java.util.List;

public class MainClass {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();

        Person p1 = new Person();
        p1.setName("Elbert");
        session.persist(p1);

        Person p2 = new Person();
        p2.setName("simon");
        session.persist(p2);

        t.commit();

        TypedQuery q = session.createQuery("select P from Person P");
        List< Person > data = q.getResultList();
        for (Person e: data) {
            System.out.println(e.getPersonId() + ":" + e.getName());
        }
    }
}