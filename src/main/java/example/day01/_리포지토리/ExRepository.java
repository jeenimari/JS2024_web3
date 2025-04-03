package example.day01._리포지토리;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//엔티티(테이블) 조작(DML) 하는 인터페이스 insert update delete select
//해당 인터페이스에 JpaRepository<조작할엔티티 클래스명, 해당 엔티티의 ID 타입> 상속
//< > : 제네릭
@Repository
public interface ExRepository
        extends JpaRepository<ExamEntitiy,String> {


}
