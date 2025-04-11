package example.day05.controller;

import example.day05.model.dto.OfficeDto;
import example.day05.service.OfficeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/day05/office")
@RequiredArgsConstructor
@CrossOrigin("*")
public class OfficeController {

    private OfficeService officeService;

    //비품등록
    @PostMapping

    public OfficeDto officeSave(@RequestBody OfficeDto officeDto){return officeService.officeSave(officeDto);}


    //전체비품 조회 화면
    @GetMapping
    public List<OfficeDto> FindAll(){
        return officeService.findAll();
    }

    //개별 비품 조회
    @GetMapping("/view")
    public OfficeDto findBya(@RequestParam int oid){
        return officeService.findBya(oid);
    }


    //개별 비품 수정
    @PutMapping

    public OfficeDto oUpdate(@RequestBody OfficeDto officeDto){
        return officeService.oUpdate(officeDto);

    }


}//c  end
