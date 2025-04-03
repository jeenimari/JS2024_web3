package example.day01._엔티티;

import jakarta.persistence.*;

@Entity //해당 클래스를 DB테이블과 매핑관계 주입(ORM)
@Table(name="exam2") //DB 테이블명 정의 , 생략시 클래스명으로 정의됨
public class ExamEntitiy2 {
    @Id // primary key 제약조건 정의
    @GeneratedValue(strategy = GenerationType.IDENTITY ) // 생성된 값
    private long id;
    @Column(nullable = true , unique = false)
    private String col1;;

    @Column(nullable = false , unique = true)
    private String col2;

    @Column(columnDefinition = "varchar(30)")
    private String col3;
}
