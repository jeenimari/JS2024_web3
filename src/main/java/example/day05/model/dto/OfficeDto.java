package example.day05.model.dto;

import example.day05.model.entity.OfficeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class OfficeDto {
    private int oid; //비품번호
    private String oname;//비품이름
    private int oamount; //비품갯수
    private LocalDateTime createOffice; //비품 등록날짜

    //dto --> entity 변환함수
    public OfficeEntity officeEntity(){
        return OfficeEntity.builder()
                .oid(oid)
                .oname(oname)
                .oamount(oamount)
                .build();
    }


}
