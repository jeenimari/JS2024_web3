package example.day01._리포지토리;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional //아래 메소드에서 하나라도 SQL 문제 발생하면 전체 취소.
public class ExamService {
    private final ExRepository exRepository;

    public boolean post(ExamEntitiy examEntitiy){
        System.out.println("exRepository = " + exRepository);
        return true;
    }

    public List<ExamEntitiy>get(){
        List<ExamEntitiy>examEntitiyList = exRepository.findAll();
        return examEntitiyList;
    }

    //수정
    public boolean put(ExamEntitiy examEntitiy){
        exRepository.save(examEntitiy);
        return true;
    }

    //수정 : 존재하는 ID만 수정

    public boolean put2(ExamEntitiy examEntitiy){
        Optional<ExamEntitiy>optionalExamEntitiy =
        exRepository.findById(examEntitiy.getStudentId());
        //2.만약에 조회한 엔티티가 있으면 .isPresent()
        if(optionalExamEntitiy.isPresent()){
            //옵셔널 객체에서 영속된 엔티티 꺼내기
            ExamEntitiy entitiy = optionalExamEntitiy.get();
            entitiy.setName(entitiy.getName());
            entitiy.setKor(entitiy.getKor());
            entitiy.setEng(entitiy.getEng());
            return true;
        }
        return false;

    }

    //삭제

    public boolean delete(String id){
        exRepository.deleteById(id);
        System.out.println(exRepository.count());
        return true;
    }
}
