package example.day05.model.entity;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)// 해당 클래스를 JPA 감사 활성화
@Getter
public class BaseTime {
    //1.생성날짜
    @CreatedDate //엔티티 생성날짜 시간 주입
    private LocalDateTime createAt;



    //2.수정 날짜
    @LastModifiedDate // 엔티티의 수정 날짜/시간 주입
    private LocalDateTime updateAt;

}
