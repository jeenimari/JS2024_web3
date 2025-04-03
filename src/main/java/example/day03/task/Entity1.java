package example.day03.task;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "coures")
@Data
@Builder
public class Entity1 extends BaseTime{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int 과정번호;

    @Column(nullable = false,length = 30)
    private String 과정명;

    @ToString.Exclude //순환참조 방지
    @Builder.Default
    @OneToMany(mappedBy = "entity1",cascade = CascadeType.ALL)
    private List<Entitiy2>entitiy2List= new ArrayList<Entitiy2>();

    //+to do
    public Dto1 dto1(){
        return Dto1.builder()
                .과정번호(this.과정번호)
                .과정명(this.과정명)
                .build();
    }
}
