package example.day03.task;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
public class Dto1 {

    private int 과정번호;

    private String 과정명;


    private Entity1 entity1; // ? 헷갈림




    public Entity1 toentity(){
        return Entity1.builder()
                .과정번호(this.과정번호)
                .과정명(this.과정명)
                .build();
    }



}
