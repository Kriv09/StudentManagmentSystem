package om.school.studentmanagement.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "students")
public class Student {
    private static String DEFAULT_VALUE_NOTSET = "Not set";

    public static final Student EMPTY = new Student(
            Long.MIN_VALUE, DEFAULT_VALUE_NOTSET, DEFAULT_VALUE_NOTSET, DEFAULT_VALUE_NOTSET, DEFAULT_VALUE_NOTSET
    );

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long Id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;


    @Column(name = "password_encrypted")
    private String password;
}
