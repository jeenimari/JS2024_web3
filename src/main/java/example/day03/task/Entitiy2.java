package example.day03.task;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "student")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Entitiy2 extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int 학생번호;


    @Column(nullable = false , length = 10)
    private String 학생명;


    @ManyToOne //단방향
    private Entity1 entity1;


    //+to do
    public Dto2 dto2(){
        return Dto2.builder()
                .학생번호(학생번호)
                .학생명(학생명)
                .과정번호(this.entity1.get과정번호())
                .build();

    }

}
