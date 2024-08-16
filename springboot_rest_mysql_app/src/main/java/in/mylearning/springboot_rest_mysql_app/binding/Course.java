package in.mylearning.springboot_rest_mysql_app.binding;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

/**
 * @author Vikas Jadhav
 */

@Data
@Entity
@Table(name = "course_data")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer cid;

    @Column(name = "course_name")
    private String name;

    @Column(name = "course_price")
    private double price;

    @Column(name = "enrollment_date")
    private LocalDate enrollment_date;
}
