package example.day02.BaseTime;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Service // 비즈니스 로직담당
@Transactional // 해당 메소드의 SQL 사용할 경우 트랜잭션 적용
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public boolean post(@RequestBody BookEntity bookEntity){
        //JPA 이용한 영속성 넣기
        bookRepository.save(bookEntity);

        return true;
    }

    //전체조회

    public List<BookEntity> get(){
        List<BookEntity>bookList = bookRepository.findAll();

        return bookList;
    }

    //수정

    public boolean put(@RequestBody BookEntity bookEntity){
       // 3. JPA 이용한 영속된/DB테이블(레코드) 조회
        BookEntity entity = bookRepository.findById(bookEntity.get도서번호()).orElse(null);
        if(entity == null)return false;
        //조회된 영속객체를 수정
        entity.set도서명(bookEntity.get도서명());
        entity.set저자(bookEntity.get저자());
        entity.set연도(bookEntity.get연도());
        entity.set출판사(bookEntity.get출판사());
        return true;
    }



    //삭제

    public boolean delete(@RequestParam int 도서번호){

        bookRepository.deleteById(도서번호);
        System.out.println(bookRepository.count());
        return true;
    }
}
