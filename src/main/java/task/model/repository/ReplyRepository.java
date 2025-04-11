package task.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import task.model.entity.ReplyEntity;
import task.model.entity.TaskEntity;

import java.util.List;

@Repository
public interface ReplyRepository extends JpaRepository<ReplyEntity,Integer> {
    //List<ReplyEntity> findByBno(int bno);

}
