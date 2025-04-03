package example.day03._자바참조관계;

import lombok.Data;

import java.util.List;

@Data// 롬복:
public class Category {
    //클래스 : 인스턴스객체)의 설계/타입
    private String 카테고리명; //멤버변수 선언 (참조타입)
    private int 카테고리번호; //멤버변수 선언 (기본타입)
//    private Board board;

    private List<Board>boardList; //양방향 참조 . 1:다 참조 , 하나의 카테고리가 여러개 게시물 참조.


}
