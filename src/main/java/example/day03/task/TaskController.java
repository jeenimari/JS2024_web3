package example.day03.task;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/day03/task")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    //1.과정등록
    @PostMapping
    public boolean post(@RequestBody Dto1 dto1){
        System.out.println("TaskController.post");
        boolean result = taskService.post(dto1);
        return result;
    }



    //2.과정 전체 조회
    @GetMapping("/course")
    public List<Dto1>get(){

        System.out.println("TaskController.get");
        return taskService.get();
    }



    //3.특정 과정에 수강생 등록
    @PostMapping("/student")
    public boolean post2(@RequestBody Dto2 dto2){
        return taskService.post2(dto2);
    }

    //4.특정 과정에 수강생 전체 조회

    @GetMapping("/student")
    public List<Dto2>get2(@RequestParam int 과정번호){
        System.out.println("TaskController.get2");
        return taskService.get2(과정번호);
    }

}
