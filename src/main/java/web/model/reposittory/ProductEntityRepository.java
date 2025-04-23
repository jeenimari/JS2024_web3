package web.model.reposittory;

import org.springframework.data.jpa.repository.JpaRepository;
import web.model.entity.ProductEntity;

public interface ProductEntityRepository  extends JpaRepository<ProductEntity,Long> {
    

}
