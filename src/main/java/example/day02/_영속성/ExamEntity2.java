package example.day02._영속성;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "examp")
@Data
public class ExamEntity2 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;


}
