package in.mylearning.springboot_rest_mysql_app.binding;

import jakarta.persistence.*;
import lombok.Data;

/**
 * @author Vikas Jadhav
 */

@Data
@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer cid;
    private String name;
    private double price;
}
