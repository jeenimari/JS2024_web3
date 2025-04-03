package example.day03._JPA연관관계;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@Entity //해당클래스는 데이터베이스와 영속관계
@Table(name = "day03category") //db테이블명 정의
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //database에 autoincrement넣어줌
    private int cno; // 카테고리번호
    private String cname; // 카테고리명

    //+양방향 , 게시물 여러개 참조
    @ToString.Exclude
    @Builder.Default
    @OneToMany(mappedBy = "category",cascade = CascadeType.ALL , fetch = FetchType.EAGER) // 양방향 참조 테이블은 자바에서만 참조한다.
    private List<Board>boardList = new ArrayList<>();


    /*
    *  - 영속성 제약조건 옵션 ( 1.cascade 2.fetch)
    * [1] @OnToMany(cascade = CascaedType.XXX)
    * 1.cascade = CascadeType.All : 부모 PK가 삭제/수정/저장되면 자식 FK도 같이 삭제 수정 저장됨
    * 2.cascade = CascadeType.REMOVE : 부모PK가 삭제 되면 자식 FK도 같이 삭제됨
    * 3.cascade = CascadeType.MERGE : 부모 PK가 수정되면 자식 FK도 같이수정됨 -> MERGE
    * 4.cascade = CascadeType.DETACH : 부모[PK]가 영속성 해지하면 자식[FK]도 같이 해제됨 -> DETACH
    * 5.cascade = CascadeType.PERIST : 부모가 저장되면 자식도 같이 저장됨 -> PERIST
    * 6.cascade = CascadeType.REFRESH : 부모가pk가 새로 불러올때 자식fk 같이 새로 불러옴(새로고침) -> REFRESH
     *
     * [2] @OneToMany(fetch = FetchType.xxx)
     * 1. fetch = FetchType.EAGER : (기본값 ) : 해당 엔티티를 조회할때 참조되는 모든 객체를 즉시 불러옴.
     *  단점 : 불필요한 엔티티가 모두 가져옴
     *
     * 2. fetch = FetchType.LAZY  :지연 로딩 : 해당 엔티티를 조회할때 참조되는 객체를 불러오지 않고 .getXXX()등 참조할때 참조되는 객체를 불러옴
     *   장점 : 초기 로딩 빠름.
    * */

}
