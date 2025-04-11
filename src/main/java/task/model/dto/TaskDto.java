package task.model.dto;

import example.day04.model.entity.TodoEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import task.model.entity.TaskEntity;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class TaskDto {
//    책 제목 , 저자 , 소개내용  ,책번호  비밀번호
    private int bno;  //책번호
    private String title; // 책 제목
    private String author; // 책 저자
    private String content; // 소개내용
    private String pw; // 비밀번호
    private LocalDateTime createAt; // + 등록 날짜

    public TaskEntity taskEntity(){
        return TaskEntity.builder()
                .bno(bno)
                .title(title)
                .author(author)
                .content(content)
                .pw(pw)
                .build();
    }


}
