package example.day03.task;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Repository2 extends JpaRepository<Entitiy2,Integer> {


}
