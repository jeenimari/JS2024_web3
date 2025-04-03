package example.day02.BaseTime;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/day02/book")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    //등록
    @PostMapping
    public boolean post(@RequestBody BookEntity bookEntity){
        System.out.println("BookController.post");
        boolean result = bookService.post(bookEntity);
        return result;
    }

    //전체조회
    @GetMapping
    public List<BookEntity> get(){
        System.out.println("BookController.get");
        return bookService.get();
    }

    //수정
    @PutMapping
    public boolean put(@RequestBody BookEntity bookEntity){
        System.out.println("BookController.put");
        return bookService.put(bookEntity);
    }



    //삭제
    @DeleteMapping
    public boolean delete(@RequestParam int 도서번호){
        System.out.println("BookController.delete");
        return bookService.delete(도서번호);
    }



}
