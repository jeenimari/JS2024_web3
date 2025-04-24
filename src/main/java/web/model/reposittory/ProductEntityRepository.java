package web.model.reposittory;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
        //(*)카테고리별 제품키워드 검색
    //SQL 문에서 매개변수 사용시 매개변수앞에 :(콜론) vs 마이바티스 #{매개변수} vs Dao ?
    @Query(value = "select * form product" +
            " where ( :cno IS NULL OR : cno= 0 OR cno = :cno)" + // sql에서는 isnull이 널과 같다는거임
            "AND( : keyword IS NULL OR pname Like %:keyword% )" , nativeQuery = true)
    Page<ProductEntity> findBySearch(Long cno, String keyword, Pageable pageable );

    //방법4 *JPQL

}
