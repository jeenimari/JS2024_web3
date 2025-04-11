package example.day05.model.respository;

import example.day05.model.entity.OfficeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfficeRepository extends JpaRepository<OfficeEntity,Integer> {
    // JPA Repository
    // 1. .save() 2.findById() 3.findAll() 4.deleteById() 등등 미리 만들어진 CRUD 메소드 제공

    // 2. 쿼리메서드(JPQL 이용한 메소드 이름 기반으로 자동 생성 )
    // Spring JPA에서 SQL 문장을 직접 작성하지 않고 메소드 이름으로 쿼리 생성한다. < 카멜표기법 >

    // 1. 비품 이름으로 조회
    List<OfficeEntity> findByOname(String oname);

    // 2. 비품 이름에 특정 키워드가 포함된 비품 조회
    List<OfficeEntity> findByOnameContaining(String keyword);

    // 3. 비품 갯수가 특정 값보다 큰 비품 조회
    List<OfficeEntity> findByOamountGreaterThan(int amount);

    // 4. 비품 갯수가 특정 범위 내에 있는 비품 조회
    List<OfficeEntity> findByOamountBetween(int start, int end);

    // 5. 비품 이름으로 정렬하여 조회
    List<OfficeEntity> findAllByOrderByOnameAsc();

    // 6. 비품 갯수가 특정 값보다 크고 이름에 특정 키워드가 포함된 비품 조회
    List<OfficeEntity> findByOamountGreaterThanAndOnameContaining(int amount, String keyword);

    // 7. 특정 비품 이름의 존재 여부 확인
    boolean existsByOname(String oname);

    // 8. 특정 비품 이름의 개수 조회
    long countByOname(String oname);

    // 9. 특정 비품 이름으로 데이터 삭제
    void deleteByOname(String oname);

    // 3. 네이티브 쿼리 사용 예시
    // 10. 네이티브 SQL로 비품 이름으로 조회
    @Query(value = "SELECT * FROM office WHERE oname = :oname", nativeQuery = true)
    List<OfficeEntity> findByOnameNative(String oname);

    // 11. 네이티브 SQL로 비품 이름에 키워드가 포함된 비품 조회
    @Query(value = "SELECT * FROM office WHERE oname LIKE %:keyword%", nativeQuery = true)
    List<OfficeEntity> findByOnameContainingNative(String keyword);

    // 12. 네이티브 SQL로 비품 갯수 업데이트
    @Modifying
    @Query(value = "UPDATE office SET oamount = :amount WHERE oid = :id", nativeQuery = true)
    int updateOamountByNative(int id, int amount);
}