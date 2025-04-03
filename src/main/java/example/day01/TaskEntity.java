package example.day01;

import example.day02.BaseTime.BaseTime;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "book")
@Data
public class TaskEntity extends BaseTime {

    //도서ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bid;

    //도서명
    @Column(nullable = false)
    private String bname;

    //저자
    @Column
    private String author;

    //출판사
    @Column
    private String publisher;

    //출판 연도
    @Column
    private int year;



}
