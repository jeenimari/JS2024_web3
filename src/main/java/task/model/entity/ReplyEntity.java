package task.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import task.model.dto.ReplyDto;

@Entity
@Table(name="reply")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class ReplyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rno;  //댓글번호
    private String rcontent; //리플내용
    private String rpw; // 리플 비밀번호



    @ManyToOne
    private TaskEntity taskEntity;

    public ReplyDto replyDto(){

        return ReplyDto.builder()
                .rno(rno)
                .rcontent(rcontent)
                .rpw(rpw)

                .build();
    }

}
