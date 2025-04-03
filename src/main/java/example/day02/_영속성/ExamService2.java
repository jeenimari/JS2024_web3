package example.day02._영속성;


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ExamService2 {

    // 엔티티 매니저 이용한 영속성 조작
    @PersistenceContext // 영속성 컨텍스트(영속성들이 저장된 메모리) 의 객체 주입
    private EntityManager entityManager;

    public void get(){
        //1.비영속성 상태 (Transient) : 순수한 자바 객체 = new
        ExamEntity2 examEntity2 = new ExamEntity2();
        examEntity2.setName("유재석");
        System.out.println("비영속상태:" + examEntity2);

        //2.영속 상태 (persistent ) , .persist(객체);
        entityManager.persist(examEntity2); //영속성 부여한다.
        System.out.println("★ 영속상태 : " + examEntity2);
        //★ 영속된 상태에서 수정
        examEntity2.setName("강호동");
        entityManager.flush(); // 트래잭션 중간에 commit(완료)
        System.out.println("★영속상태:" + examEntity2);

        //3. 준 영속 상태(Detached).detach(영속된 객체); 영속성(연결) 해제
        entityManager.detach(examEntity2);
        System.out.println("★비영속상태:" + examEntity2);
        examEntity2.setName("신동엽");
        entityManager.flush();
        System.out.println("★비영속상태 : " + examEntity2);
        //4.삭제 상태(Remove).remove(영속된 객체) 영속 삭제 = 레코드가 삭제됨
        //준영속 상태에서는 삭제 안됨
        entityManager.remove(examEntity2);
    }
}
