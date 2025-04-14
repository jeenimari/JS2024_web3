package web.model.reposittory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import web.model.entity.MemberEntity;

@Repository
public interface MemberEntityRepository extends JpaRepository<MemberEntity,Integer> {
    // 상속하므로서 JPA 기본적인 CRUD 제공
}
