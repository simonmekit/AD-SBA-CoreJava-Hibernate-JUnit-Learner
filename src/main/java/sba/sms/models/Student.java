package sba.sms.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.Helper;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Student is a POJO, configured as a persistent class that represents (or maps to) a table
 * name 'student' in the database. A Student object contains fields that represent student
 * login credentials and a join table containing a registered student's email and course(s)
 * data. The Student class can be viewed as the owner of the bi-directional relationship.
 * Implement Lombok annotations to eliminate boilerplate code.
 */

@NoArgsConstructor
@AllArgsConstructor
//@RequiredArgsConstructor // handled by AllArgsConst
@Setter
@Getter
@ToString
@Entity
@Table(name = "student")
public class Student {
    @Id
    @Column(name = "email", nullable = false)
    @Size(max = 50)
    private String email;

    @Column(name = "name", nullable = false)
    @Size(max = 50)
    private String name;

    @Column(name = "password", nullable = false)
    @Size(max = 50)
    private String password;

    @ManyToMany(targetEntity = Course.class, cascade = {CascadeType.DETACH,
            CascadeType.MERGE, CascadeType.REMOVE, CascadeType.PERSIST},
            fetch = FetchType.EAGER)
    @JoinTable(name = "student_courses",
            joinColumns = {@JoinColumn(name = "student_email")},
            inverseJoinColumns = {@JoinColumn(name = "courses_id")})
    private Set<Course> courses;

}



