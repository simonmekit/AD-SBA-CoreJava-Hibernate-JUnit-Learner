package sba.sms.models;

import jakarta.persistence.*;

@Entity
@Table
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int PersonId;
    private String name;

    // Declare Address class for relationship
    @OneToOne(cascade = CascadeType.ALL)
    private Address myaddress;

    public Person(int personId, String name) {
        PersonId = personId;
        this.name = name;
    }

    public Person() {}
    public int getPersonId() {
        return PersonId;
    }

    public void setPersonId(int personId) {
        PersonId = personId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}