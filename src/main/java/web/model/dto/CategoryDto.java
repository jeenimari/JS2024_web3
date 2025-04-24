package web.model.dto;

import lombok.*;
import web.model.entity.CategoryEntity;

@Getter
@Setter@ToString@AllArgsConstructor@NoArgsConstructor@Builder
public class CategoryDto {
    private long cno;
    private String cname;


    //*todo 주로 호출용도로 많이쓰이
    public static CategoryDto toDto(CategoryEntity categoryEntity){
        return CategoryDto.builder()
                .cno(categoryEntity.getCno())
                .cname(categoryEntity.getCname())
                .build();
    }
}
