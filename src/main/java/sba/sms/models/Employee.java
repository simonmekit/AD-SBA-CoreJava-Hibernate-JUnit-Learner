package sba.sms.models;

import jakarta.persistence.*;
import lombok.*;

import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int empId;

    @Column(name = "empname", nullable = false)
    @Size(max = 50)
    private String empName;

    @Column(name = "email", nullable = false)
    @Size(max = 50)
    private String email;

    @Column(name = "password", nullable = false)
    @Size(max = 50)
    private String password;

    public Employee(String name, String email, String password) {
        this.empName = name;
        this.email = email;
        this.password = password;
    }
}
