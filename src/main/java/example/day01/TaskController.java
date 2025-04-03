package example.day01;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/day01/task1")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;
 //도서 등록
    @PostMapping
    public boolean post(@RequestBody TaskEntity taskEntity){
        boolean reuslt = taskService.post(taskEntity);
        return reuslt;
    }

    //도서 전체 조회
    @GetMapping
    public List<TaskEntity>get(){
        return taskService.get();
    }


    //도서 정보 수정
    @PutMapping
    public boolean put(@RequestBody TaskEntity taskEntity){
        boolean result = taskService.put(taskEntity);
        return result;
    }

    //삭제
    @DeleteMapping
    public boolean delete(@RequestParam int bid){
        boolean result = taskService.delete(bid);
        return  result;
    }
}
