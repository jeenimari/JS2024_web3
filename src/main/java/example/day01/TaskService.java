package example.day01;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class TaskService {
    private final TaskRepository taskRepository;

    //도서등록
    public boolean post(TaskEntity taskEntity){
        System.out.println("taskRepository = " + taskRepository);
        taskEntity.setBid(0);
        taskRepository.save(taskEntity);
        return true;
    }

    //도서 조회

    public List<TaskEntity>get(){
        List<TaskEntity>taskList = taskRepository.findAll();
        return taskList;
    }

    //도서 정보수정
    public boolean put(TaskEntity taskEntity){
        Optional<TaskEntity>optionalTaskEntity =
                taskRepository.findById(taskEntity.getBid());
        if(optionalTaskEntity.isPresent()){
            TaskEntity existingEntity  = optionalTaskEntity.get();

            existingEntity.setBname(taskEntity.getBname());
            existingEntity.setAuthor(taskEntity.getAuthor());
            existingEntity.setPublisher(taskEntity.getPublisher());
            existingEntity.setYear(taskEntity.getYear());
            return true;
        }
        return false;

    }

    // 삭제
    public  boolean delete(int bid){
        taskRepository.deleteById(bid);
        System.out.println(taskRepository.count());
        return true;

    }

}
