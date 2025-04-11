package example.day05.model.entity;

import example.day05.model.dto.OfficeDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity // 데이터베이스의 테이블과 영속 관계
@Table(name ="office") //데이터베이스 테이블명 정의
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder //룸북
public class OfficeEntity extends BaseTime {

    @Id //Pk
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    private int oid; //비품번호
    private String oname;//비품이름
    private int oamount; //비품갯수

    //엔티티 -> 디티오 변환함수
    public OfficeDto officeDto(){
        //엔티티에서 dto 변환 필드 선택하여 dto객체만들기
        return OfficeDto.builder()
                .oid(oid)
                .oname(oname)
                .oamount(oamount)
                .createOffice(this.getCreateAt()) //base Time
                .build();
    }


}
