package example.day02.BaseTime;

import jakarta.persistence.*;
import lombok.Data;

@Entity //해당클래스는 DB테이블과 매핑
@Table(name = "day02book") //db 테이블 명 정의
@Data//롬복
public class BookEntity {
    //도서번호
    @Id // pk
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int 도서번호; // auto increment 자동번호 부여하기 위한 int 타입선정
    //도서명
    @Column(nullable = false,length = 30) // not null, vcarchar(30)
    private String 도서명;
    //저자
    @Column(nullable = false,length = 30) // not null , varchar(100)
    private String 저자;

    //출판사
    @Column(nullable = false,length = 50)
    private String 출판사;
    //출판연도

    @Column
    private int 연도;

}
