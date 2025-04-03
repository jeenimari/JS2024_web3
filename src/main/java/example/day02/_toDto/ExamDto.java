package example.day02._toDto;

//DTO 목적 : 서로 다른 계층/레이어 간의 이동 객체
//VO 목적 :수정불가능한 객체 ( Setter 없음/ 불변 ) vo = value object 읽기모드
//* 사용되는 계층 : 컨트롤러 레이어 (view<-->controller , controller<--->service)

import lombok.Builder;
import lombok.Data;

@Data
@Builder //빌더 패턴
public class ExamDto {
    private String id;
    private String btitle;
    private int price;

    //(2)dto -> entitiy 객체로 반환 함수
    public ExamEntity1 toEntity1() {
        return ExamEntity1.builder().
                id(this.id)
                .btitle(this.btitle)
                .price(this.price)
                .build();
    }
}
