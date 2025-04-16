package web.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import web.model.dto.MemberDto;

@Entity
@Table(name = "member")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder

public class MemberEntity {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)// 기본키
    private int mno;

    @Column(unique = true)  // 이메일을 고유값으로 설정
    private String memail;
    private String mpwd;
    private String mname;

    // entity --> dto

    public MemberDto toDto(){
        return MemberDto.builder()
                .mno( mno )
                .memail( memail )
                .mname( mname )
                .build();
    }
}
