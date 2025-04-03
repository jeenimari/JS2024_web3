package example.day01._리포지토리;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/day01/jpa")
@RequiredArgsConstructor
public class ExamController {
    private final ExamService examService;
    //1.C: 등록
    @PostMapping
    public boolean post(@RequestBody ExamEntitiy examEntitiy){
        boolean result = examService.post(examEntitiy);
        return result;
    }


    @GetMapping
    public List<ExamEntitiy>get(){
        return examService.get();
    }

    //수정
    @PutMapping
    public boolean put(@RequestBody ExamEntitiy examEntitiy){
        boolean result = examService.put(examEntitiy);
        return result;
    }

    //삭제
    @DeleteMapping
    public boolean delete(@RequestParam String id){
        boolean result = examService.delete(id);
        return result;
    }
}
