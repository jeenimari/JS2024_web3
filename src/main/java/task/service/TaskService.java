package task.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.web.bind.annotation.*;
import task.model.dto.ReplyDto;
import task.model.dto.TaskDto;
import task.model.entity.ReplyEntity;
import task.model.entity.TaskEntity;
import task.model.repository.ReplyRepository;
import task.model.repository.TaskRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor

public class TaskService {

    private final TaskRepository taskRepository;
    private final ReplyRepository replyRepository;


    // 1.  책 추천 개별 등록

    public TaskDto bookSave(TaskDto taskDto ){

    // 1. dto 를 entity 변환하기
    TaskEntity taskEntity = taskDto.taskEntity();
    // 2. entity를 save(영속화/db레코드 매칭/등록) 한다.
        TaskEntity saveEntity = taskRepository.save( taskEntity );
    // 3. save 로 부터 반환된 엔티티(영속화)된 결과가 존재하면
        if( saveEntity.getBno() > 0 ){
        return saveEntity.taskDto(); // entity를 dto로 변환하여 반환
    }else{ // 결과가 존재하지 않으면
        return null; // null 반환
    }

}
    //2.책 추천 수정 ( 비밀번호 기반)
    public TaskDto bookUpdate(TaskDto taskDto ){

        return taskRepository.findById(taskDto.getBno())
                .map((taskEntity -> {
                    taskEntity.setTitle(taskDto.getTitle());
                    taskEntity.setAuthor(taskDto.getAuthor());
                    taskEntity.setContent(taskDto.getContent());
                    taskEntity.setPw(taskDto.getPw());
                    return taskEntity.taskDto();
                }))
                .orElse(null);

    }// f end

    //3.책 추천 삭제

   public boolean bookDelete(int bno ){
      return taskRepository.findById(bno)
              .map((taskEntity) -> {
                  taskRepository.deleteById(bno);
                  return true;
       }).orElse(false);

    }


    //4.책 추천 조회 전체조회
    public List<TaskDto> bookFindAll( ){

    List<TaskEntity> taskEntityList = taskRepository.findAll();

    //2.모든 엔티티 리스트를 디티오 리시트로 변환
    List<TaskDto>taskDtoList = new ArrayList<>(); //2-1  : dto리스트 생성
    return taskRepository.findAll().stream().map(TaskEntity::taskDto).collect(Collectors.toList());

    }// f end

    // 5. 개별 조회
   public TaskDto taskFindByBno( int bno ){
       Optional< TaskEntity > optional
               = taskRepository.findById( bno );

       if(optional.isPresent()){
           TaskEntity taskEntity = optional.get();
           TaskDto taskDto = taskEntity.taskDto();
           return taskDto;

       }// if end
       return null;


    }// f end



    //6. 리뷰 작성기능 --익명으로 해당 책에 대한 감상평 남기기 비밀번호 설정하여 삭제 가능--

        public ReplyDto replySave(ReplyDto replyDto){

            // 1. dto 를 entity 변환하기
            ReplyEntity replyEntity = replyDto.replyEntity();
            // 2. entity를 save(영속화/db레코드 매칭/등록) 한다.
            ReplyEntity saveEntity = replyRepository.save( replyEntity );

            // ---- 리플 저정후 fk 도 저장
            // 현재 fk 의 엔티티 찾기
            TaskEntity taskEntity = taskRepository.findById( replyDto.getBno() ).orElse(null);

            saveEntity.setTaskEntity( taskEntity );


            // 3. save 로 부터 반환된 엔티티(영속화)된 결과가 존재하면
            if( saveEntity.getRno() > 0 ){
                return saveEntity.replyDto(); // entity를 dto로 변환하여 반환
            }else{ // 결과가 존재하지 않으면
                return null; // null 반환
            }
    }






//7.. 리뷰 삭제 (비밀번호 기반) 사용자가 등록한 비밀번호 입력 후 삭제 가능


    public boolean replyDelete(@RequestParam int rno){
        boolean result = replyRepository.existsById(rno);
        if(result==true){
            replyRepository.deleteById(rno);
            return true;
        }
        return false;
    }




//8. 책 별 리뷰 전체 조회  특정 책에 작성된 모든 리뷰를 조회 가능
    public List<ReplyDto> replyView(int bno){
        // 책 ID로 리뷰 찾기 (bno는 책 번호라고 가정)
        //List<ReplyEntity> replyEntities = replyRepository.findByBno(bno);
        Optional<TaskEntity> replyEntities = taskRepository.findById( bno );

        // Entity 리스트를 DTO 리스트로 변환
        List<ReplyDto> replyDtoList = new ArrayList<>();
        for(ReplyEntity entity : replyEntities.get().getReplyEntities()) {
            replyDtoList.add(entity.replyDto());
        }

        return replyDtoList;
    }

//    // 특정 책에 대한 모든 리뷰 조회
//    public List<ReplyDto> replyUpdate(int rno) {
//        List<ReplyEntity> replyEntityList = replyRepository.findAllById(rno);
//        List<ReplyDto> replyDtoList = new ArrayList<>();
//
//        for(int i = 0; i < replyEntityList.size(); i++) {
//            ReplyDto replyDto = entityToDto(replyEntityList.get(i));
//            replyDtoList.add(replyDto);
//        }
//
//        return replyDtoList;
//    }
}
