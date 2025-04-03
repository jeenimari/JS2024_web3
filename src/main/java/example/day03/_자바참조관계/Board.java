package example.day03._자바참조관계;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@Builder //빌더

public class Board {

    private int 게시물번호;
    private String 게시물제목;
    private String 게시물내용;
    private Category category; //board가 --> category 참조
    //양방향 참조 , 하나의 게시물이 여러개 댓글 참조.
    @ToString.Exclude // 해당필드는 toString 메소드에 제외시키는 어노테이션임
    private List<Reply>replyList;
}
