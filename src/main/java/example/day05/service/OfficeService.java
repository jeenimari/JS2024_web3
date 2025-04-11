package example.day05.service;


import example.day05.model.dto.OfficeDto;
import example.day05.model.entity.OfficeEntity;
import example.day05.model.respository.OfficeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class OfficeService {

    private final OfficeRepository officeRepository;


        //1.비품등록
        public OfficeDto officeSave( OfficeDto officeDto){

            //1.dto를 entitiy로 변환하기
            OfficeEntity officeEntity = officeDto.officeEntity();
            //2.entitiy를 save 영속화(db레코드 매칭,등록)함
            OfficeEntity saveOffice = officeRepository.save(officeEntity);
            //3.save 로부터 반환된 엔티티된 결과가 존재하면
            if(saveOffice.getOid()>0){
                return officeEntity.officeDto();  //엔티티를 디티오로변환
            }else {   //결과가 존재하지 않으면 null 반환
                return null;
            }

        }


        //전체비품 조회 화면

        public List<OfficeDto> findAll(){
          //[방법1]
            List<OfficeEntity>officeEntities = officeRepository.findAll();
            //모든 엔티티 리스트를 디티오 리스트로 변환
            List<OfficeDto>officeDtos = new ArrayList<>(); //디티오리스트 생성
            for(int i=0; i<officeEntities.size();i++){
                OfficeDto officeDto = officeEntities.get(i).officeDto(); //i번째 entitiy를 dto로 변환
                officeDtos.add(officeDto); //  dto리스트에 저장
            }// for end
            return officeDtos;
        }

        //개별 비품 조회

        public OfficeDto findBya( int oid ){
           //[방법1] 일반적인 ==================================//
            //1.pk이용한 entity 조회하기 . findBya()
            //Optinal 클래스 : null 제어하는 메소드들을 제공하는 클래스
            Optional<OfficeEntity>optional
                    = officeRepository.findById(oid);

            //2.조회한 결과가 존재하면 , isPrsent()
            if(optional.isPresent()){
                OfficeEntity officeEntity = optional.get();
                //dto로 변환
                OfficeDto officeDto = officeEntity.officeDto();

                return officeDto;

            }
            return null;
        }


        //개별 비품 수정


        public OfficeDto oUpdate(OfficeDto officeDto){
            //[방법1]일반적인 ================================//
            //1.수정할 id로 엔티티 조회
            Optional<OfficeEntity>optional = officeRepository.findById(officeDto.getOid());
            //2.존재하면 수정 존재하지않으면 null 반환 ,
            if(optional.isPresent()){
                OfficeEntity officeEntity = optional.get();

                //입력받은 dto값 엔티티에 대입
                officeEntity.setOname(officeDto.getOname());
                officeEntity.setOamount(officeDto.getOamount());
                return officeEntity.officeDto();
            }
            return  null;
        }



}
