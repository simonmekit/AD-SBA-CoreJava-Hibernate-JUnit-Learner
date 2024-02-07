package sba.sms.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.Helper;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Course is a POJO, configured as a persistent class that represents (or maps to) a table
 * name 'course' in the database. A Course object contains fields that represent course
 * information and a mapping of 'courses' that indicate an inverse or referencing side
 * of the relationship. Implement Lombok annotations to eliminate boilerplate code.
 */

@NoArgsConstructor
@AllArgsConstructor
//@RequiredArgsConstructor
@Setter
@Getter
//@Helper
@ToString
@Entity
@Table(name = "course")
public class Course {
    @Id
    @NotNull
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    @NotNull
    @Size(max = 50)
    private String name;

    @Column(name = "instructor")
    @NotNull
    @Size(max = 50)
    private String instructor;

    @ManyToMany (targetEntity = Student.class,
            cascade = {CascadeType.DETACH,CascadeType.MERGE,
                    CascadeType.REMOVE, CascadeType.PERSIST},
            fetch = FetchType.EAGER, mappedBy = "courses")
    private Set<Student> students;

}
