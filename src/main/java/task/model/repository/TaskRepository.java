package task.model.repository;

import example.day04.model.entity.TodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import task.model.entity.TaskEntity;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity,Integer> {

    List<TaskEntity> findByTitle(String title );

    List<TaskEntity>findByTitleContaining(String keyword);

    List<TaskEntity> findByTitleAndContent(String title, String content);

    boolean existsByTitle( String title );

    long countByTitle( String title );

    void deleteByTitle( String title );


    @Query( value = "select * from book where title = :title " , nativeQuery = true )
    List<TaskEntity> findByTitleNative( String title );


    @Modifying // 네이티브쿼리 (@Query)사용시 select 만 지원 하므로 insert/update/delete 쿼리는 @Modifying 같이 사용된다.
    @Query( value = "delete from book where title = :title ", nativeQuery = true )
    int deleteByNative( String title );


    @Modifying
    @Query( value = "update book set title = :title where bno = :bno " , nativeQuery = true )
    int updateByNative( int bno , String title  );

}
