package example.day02.BaseTime;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass //해당 클래스는 일반 엔티티가 아닌 상속엔티티로 사용 함
@EntityListeners(AuditingEntityListener.class)
public class BaseTime {

    //1.엔티티/레코드의 수정 날짜/시간 자동주입
    @CreatedDate
    private LocalDateTime 생성날짜시간;

    @LastModifiedDate
    private LocalDateTime 수정날짜시간; //회원수정날짜 , 제품수저일 , 주문 수정일

    private String 도서관ID = "1";
}
