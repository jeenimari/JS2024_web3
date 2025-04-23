package web.model.reposittory;

import org.springframework.data.jpa.repository.JpaRepository;
import web.model.entity.CategoryEntity;
import web.model.entity.ImgEntity;

public interface ImgEntityRepository extends JpaRepository<ImgEntity,Long> {
}
