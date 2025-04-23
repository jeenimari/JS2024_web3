package web.model.reposittory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import web.model.entity.ProductEntity;

import java.util.List;

public interface ProductEntityRepository  extends JpaRepository<ProductEntity,Long> {

    //방법1.JPA 기본적인 함수 제공
    //Save , findAll, findById , delete 등 기본적인 crud제공

    //방법2.쿼리메소드 , 규칙 : 명명규칙(카멜)

    List<ProductEntity>findByCategoryEntityCno(long cno);
    //방법3.네이티브 쿼리
    @Query(value = "select * from product where cno=:cno",nativeQuery = true)
    List<ProductEntity>nativeQuery(long cno);

    //방법4 *JPQL

}
