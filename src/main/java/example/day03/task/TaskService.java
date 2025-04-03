package example.day03.task;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class TaskService {

    private final Repository1 repository1;
    private final Repository2 repository2;

    //1.과정등록

    public boolean post(Dto1 dto1){
        //dto->entitiy 변환
        Entity1 entity1 = dto1.toentity();
        repository1.save(dto1.toentity());
        //결과 확인
        if(entity1.get과정번호()>0){return true;} // 만약에 영속결과 과정번호 존재하면 성공
        return false;
    }



    //2.과정 전체 조회

    public List<Dto1> get(){
        System.out.println("TaskController.get");
        List<Entity1>entity1List = repository1.findAll();
        return entity1List.stream()
                .map(Entity1::dto1)
                .collect(Collectors.toList());
    }



    //3.특정 과정에 수강생 등록

    public boolean post2(Dto2 dto2){
//빌드 통해 입력받은 정보 디티오 ->변환 -> 엔티티

        Entitiy2 studentEntity = Entitiy2.builder()
                .학생번호(dto2.get학생번호())
                .학생명(dto2.get학생명())
                .build();

        //등록할 엔티티 1 조회
        Entity1 course = repository1.findById(dto2.get과정번호()).orElse(null);
        if(course ==null){
            return false;
        }
        //조회한 엔티티 1을 엔티티 2에 대입 ( fk 대입)
        studentEntity.setEntity1(course);

        //영속성 부여
        Entitiy2 saveEntity = repository2.save(studentEntity);

        //결과 확인
        if(saveEntity.get학생번호()<1){
            return false;
        }
    return true;
    }


    //4.특정 과정에 수강생 전체 조회


    public List<Dto2>get2(int 과정번호){

        // 학생엔티티 2 전체조회
        List<Entitiy2> entityList = repository2.findAll();

        //2) 특정 과정 수강중인 학생엔티티를 담을 리스트선언
        List<Entitiy2>result2 = new ArrayList<>();

        //전체 학생엔티티를 순회하여 특정 번호만 조회후 add
        for(Entitiy2 entity : entityList){
            if(entity.getEntity1()!=null& entity.getEntity1().get과정번호() == 과정번호 ){
                result2.add(entity);
            }
        }

        // todto를 통해 엔티티 변환 -> 디티오
        List<Dto2>List2 = result2.stream()
                .map(아무거나->아무거나.dto2()).collect(Collectors.toList());
        return List2;



    }



}
