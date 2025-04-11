package task.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import task.model.dto.ReplyDto;
import task.model.dto.TaskDto;
import task.service.TaskService;

import java.util.List;

@RestController
@RequestMapping("/task/book")
@RequiredArgsConstructor
@CrossOrigin("*")
public class TaskController {

    private final TaskService taskService;


    // 1.  책 추천 개별 등록  0
    @PostMapping // http://localhost:8080/day04/todos
    // { "title" : "책제목1" , "author" : "김철수" , "content" : "소개내용","pw":"1234" }
    public TaskDto bookSave(@RequestBody TaskDto taskDto ){
        return taskService.bookSave( taskDto );
    }

    //2.책 추천 수정 ( 비밀번호 기반) 0
    @PutMapping
    // { "bno" : "1" , "title" : "책제목2" ,  "author" : "홍길동" , "content" : "바꾼소개내용","pw":"1234"  }
    public TaskDto bookUpdate( @RequestBody TaskDto taskDto ){
        return taskService.bookUpdate( taskDto );
    }

    //3.책 추천 삭제  0
    // http://localhost:8080/day04/todos?bno=1
    @DeleteMapping
    public boolean bookDelete( @RequestParam int bno ){
        return taskService.bookDelete( bno );
    }


    //4.책 추천 조회  0
    @GetMapping
    public List<TaskDto> bookFindAll( ){
        return taskService.bookFindAll();
    }


    // 5. 개별 조회  0  -> 리뷰도 조회되어야함
    @GetMapping("/view")
    // http://localhost:8080/day04/todos/view?bno=1
    public TaskDto taskFindByBno( @RequestParam int bno ){
        return taskService.taskFindByBno( bno );
    }



//6. 리뷰 작성기능익명으로 해당 책에 대한 감상평 남기기 비밀번호 설정하여 삭제 가능
//    http://localhost:8080/task/book/reply
//{ "rno" : "1" , "rcontent" : "이책재밌음" ,  "rpw" : "1234" , "bno" : "1"  }

    @PostMapping("/reply")
    public ReplyDto replySave(@RequestBody ReplyDto replyDto){
        return taskService.replySave(replyDto);
    }






//7.. 리뷰 삭제 (비밀번호 기반) 사용자가 등록한 비밀번호 입력 후 삭제 가능

    @DeleteMapping("/reply")
    public boolean replyDelete(@RequestParam int rno){
        return taskService.replyDelete(rno);
    }



//8. 책 별 리뷰 전체 조회  특정 책에 작성된 모든 리뷰를 조회 가능

    @GetMapping("/reply/view")
    public List<ReplyDto> replyView( @RequestParam int bno ){
        return taskService.replyView(bno);
    }







} //  cend
