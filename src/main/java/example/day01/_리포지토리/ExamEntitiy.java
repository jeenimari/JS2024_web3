package example.day01._리포지토리;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "student1") //DB 테이블 매핑
@Data
public class ExamEntitiy {

    //1.학번
    @Id
    private String studentId;

    //2.이름
    @Column(nullable = false)
    private String name;


    //3,국어점수
    @Column
    private int kor;

    //4.영어점수
    @Column
    private int eng;

}
