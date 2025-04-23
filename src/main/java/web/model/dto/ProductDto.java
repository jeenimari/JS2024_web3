package web.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
import web.model.entity.ImgEntity;
import web.model.entity.ProductEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDto {

    //* 제품 등록할때 필요한 필드
    private String pname;
    private String pcontent;
    private int pprice;
    private List<MultipartFile>files=new ArrayList<>();//제품이미지들
    private long cno;    //제품 카테고리 번호

    //* 제품 조회할때 필요한 필드
    private long pno; //제품 번호
    private int pview; // 제품 조회수
    private String memail;// 제품 등록한 회원아이디
    private String cname; // 제품 카테고리명
    private List<String>imges = new ArrayList<>(); //이미지들



    // toEntity
    public ProductEntity toEntity(){
        return ProductEntity.builder()
        .pname(pname).pcontent(pcontent).pprice(pprice)
        .build();
    }

    //*toDto : 제품 전체 조회 , 제품개발 조회 사용
    public static ProductDto toDto(ProductEntity productEntity){
        return ProductDto.builder()
                .pname(productEntity.getPname())
                .pcontent(productEntity.getPcontent())
                .pprice(productEntity.getPprice())
                .cno(productEntity.getCategoryEntity().getCno())
                .pno(productEntity.getPno())
                .pview(productEntity.getPview())
                .memail(productEntity.getMemberEntity().getMemail())
                .imges(productEntity.getImgEntityList().stream()
                        .map(ImgEntity::getIname)
                        .collect(Collectors.toList()))
                .build();
    }
}
