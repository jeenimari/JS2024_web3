package example.day02._영속성;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/day02/persis")
public class ExamController2 {
    private final ExamService2 examService2;

    @GetMapping
    public void get(){
        examService2.get();
    }

}
