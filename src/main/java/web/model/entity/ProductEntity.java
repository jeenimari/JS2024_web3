package web.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.ArrayList;
import java.util.List;

@Entity@Table(name="product")
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor //롬복
public class ProductEntity extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long pno; //제품 식별번호

        @Column(nullable = false)
    private String pname; //제품명

    @Column(columnDefinition = "longtext") //mysql 네이티브 타입 지정
    private String pcontent; //제품설명

    @Column(nullable = false)
    @ColumnDefault("0") // default 값을 0으로 지정
    private int pprice; //제품가격

    @Column(nullable = false)
    @ColumnDefault("0")
    private int pview; //조회수


    //*단방향 통신 시작 : 참조할 PK 필드가 존재하는 엔티티 필드 생성
    @ManyToOne @JoinColumn(name="mno") // fk 필드명 정의
    private MemberEntity memberEntity;

    @ManyToOne@JoinColumn(name="cno") // fk필드명 정의
    private CategoryEntity categoryEntity;


    //양방향 통신 :
    @OneToMany(mappedBy = "productEntity",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @ToString.Exclude @Builder.Default
    private List<ImgEntity>imgEntityList = new ArrayList<>();

    @OneToMany(mappedBy = "productEntity",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @ToString.Exclude @Builder.Default
    private List<ReplyEntity>replyEntityList = new ArrayList<>();

}
