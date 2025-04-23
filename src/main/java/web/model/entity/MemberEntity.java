package web.model.entity;

import jakarta.persistence.*;
import lombok.*;
import web.model.dto.MemberDto;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "member")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder

public class MemberEntity extends BaseTime {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)// 기본키
    private int mno;

    @Column(unique = true)  // 이메일을 고유값으로 설정
    private String memail;
    private String mpwd;
    private String mname;

    //양방향통신 : FK 엔티티를 여러개 가지므로 List타입으로 만듬
    @OneToMany(mappedBy = "memberEntity" , cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @ToString.Exclude //양방향 설계시 toString 롬복의 순환참조 방지
    @Builder.Default //엔티티 객체 생성시 빌드메소드 사용하면 기본값
    private List<ProductEntity>productEntityList = new ArrayList<>();

    @OneToMany(mappedBy = "memberEntity",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<ReplyEntity>replyEntityList = new ArrayList<>();

    // entity --> dto

    public MemberDto toDto(){
        return MemberDto.builder()
                .mno( mno )
                .memail( memail )
                .mname( mname )
                .build();
    }
}
