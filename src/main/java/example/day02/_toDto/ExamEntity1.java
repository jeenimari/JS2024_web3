package example.day02._toDto;

//entitiy 목적 : DB테이블의 영속/연결 된 객체
//* 사용 되는 계층 : 서비스 레이어(비즈니스 로직)

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;

@Entity
@Table(name="book1")
@Builder
public class ExamEntity1 {
    @Id // pk
    private String id;  //도서 식별번호
    private String btitle; //도서명
    private int price; // 도서가격

    //-entitiy -> dto 변환함수.
    public ExamDto toDto(){

        //this키워드 : 해당메소드를 호출한 인스턴스(객체)
        //1.일반 생성자
        //return new ExamDto(this.id,this.btitle,this.price);
        //2.빌더 패턴 , 규칙적인 생성자의 유연성 보장하는 메소드 제공 패턴
        // 클래스명.builder().필드명(값).필드명(값).필드명(값).build();
        return ExamDto.builder()
                .id(this.id)
                .btitle(this.btitle)
                .price(this.price)
                .build();

    }
}
