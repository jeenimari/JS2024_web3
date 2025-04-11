package task.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import task.model.entity.ReplyEntity;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class ReplyDto {

    private int rno;  //댓글번호
    private String rcontent; //리플내용
    private String rpw; // 리플 비밀번호
    private int bno;


    public ReplyEntity replyEntity(){
        return ReplyEntity.builder()
                .rno(rno)
                .rcontent(rcontent)
                .rpw(rpw)
                .build();
    }
}
