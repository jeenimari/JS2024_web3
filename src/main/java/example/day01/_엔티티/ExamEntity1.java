package example.day01._엔티티;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class ExamEntity1 {

    //멤버변수
    @Id //식별키
    private int coll;
    private String col2;
    private double col3;

}
