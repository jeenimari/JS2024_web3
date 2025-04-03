package example.day03.task;



import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Dto2 {
    private int 학생번호;
    private String 학생명;
    private int 과정번호;


    //엔티티로 변환 메소드
    public Entitiy2 doEntity(){
        return Entitiy2.builder().학생번호(학생번호)
                .학생명(학생명)
                .build();

    }
}
