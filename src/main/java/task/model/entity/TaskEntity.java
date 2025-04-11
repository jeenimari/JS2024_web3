package task.model.entity;

import example.day04.model.dto.TodoDto;
import jakarta.persistence.*;
import lombok.*;
import task.model.dto.TaskDto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "book") //데이터베이스 테이블 명
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskEntity extends BaseTime {

    @Id // pk 설정
    @GeneratedValue( strategy = GenerationType.IDENTITY ) // auto_increment
    private int bno;
    private String title;
    private String author; // 책 저자
    private String content; // 소개내용
    private String pw; // 비밀번호

    @OneToMany(mappedBy = "taskEntity")
    @Builder.Default
    @ToString.Exclude
    private List<ReplyEntity> replyEntities = new ArrayList<>();




    // 엔티티 --> dto 변환

    public TaskDto taskDto(){
        // entity 에서 dto로 변환할 필드 선택하여 dto객체만들기
        return TaskDto.builder()
                .bno( this.bno )
                .title( this.title )
                .author( this.author )
                .content( this.content )
                .pw( this.pw)
                .build();
    }


}
