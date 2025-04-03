package example.day02._toDto;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ExamService {

    private final ExamRepository examRepository;
    public boolean post(ExamDto examDto){
        //비영속성
       ExamEntity1 examEntity1 =  examDto.toEntity1();
        //*영속성
        examRepository.save(examEntity1);
        return true;
    }
    // 1. dto 영속성을 부여 할수 없고 DTO를 entity 변환하기

    public List<ExamDto>get(){
        //모든 영속된 /레코드 조회
        List<ExamEntity1> entity1List =
                examRepository.findAll();
        //모든 영속된 엔티티 대신에 DTO를 반환 . 스트림
        return entity1List.stream()// 스트림 생성
                .map(ExamEntity1::toDto)
                .collect((Collectors.toList()));
    }
}
