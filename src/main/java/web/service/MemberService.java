package web.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.model.dto.MemberDto;
import web.model.entity.MemberEntity;
import web.model.reposittory.MemberEntityRepository;
import web.util.JwtUtil;

import java.util.Set;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor //fianl 필드의 생성자 자동생성
@Transactional // 트랜잭션 : 여러개의 SQL을 하나의 논리단위
public class MemberService {
    private final MemberEntityRepository memberEntityRepository;


    // [1] 회원가입
    public boolean signUp(  MemberDto memberDto ){
        // 1.암호화
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); // 암호화 비크립트 객체 생성
        String hashedPwd = passwordEncoder.encode( memberDto.getMpwd() ); // 암호화 지원하는 함수 .encode( 암호화할데이터 )
        memberDto.setMpwd( hashedPwd );
        // 2. DTO 를 entity 변환하기.
        MemberEntity memberEntity = memberDto.toEntity();
        // 3. 리포지토리 이용한 entity 영속화하기 , 영속된 결과 반환
        MemberEntity saveEntity = memberEntityRepository.save( memberEntity );
        // 4. 영속된 엔티티의 (자동생성된) pk 확인
        if( saveEntity.getMno() >= 1 ){ return true;}
        return false;
    }

    private final JwtUtil jwtUtil;
    // [2] 로그인 , 로그인 성공시 token -> 실패시 null
    public String login( MemberDto memberDto ) {
        // 1. 이메일(아이디)를 DB에서 조회하여 엔티티 찾기
        MemberEntity memberEntity
                = memberEntityRepository.findByMemail(memberDto.getMemail());
        // 2. 조회된 엔티티가 없으면
        if (memberEntity == null) {
            return null;
        } // 로그인 실패
        // 3. 조회된 엔티티의 비밀번호 검증.  .matches( 입력받은패스워드 , 암호화된패스워드 )
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();// Bcrypt 객체 생성
        boolean inMath
                = passwordEncoder.matches(memberDto.getMpwd(), memberEntity.getMpwd());
        // 4. 비밀번호 검증 실패이면
        if (inMath == false) return null; // 로그인 실패
        // 5. 비밀번호 검증 성공이면 ,
        String token = jwtUtil.createToken(memberEntity.getMemail());
        System.out.println(">>>발급된 token " + token);
        //+ 레디스에 24시간만 저장되는 로그인 로그(기록)하기.
        stringRedisTemplate.opsForValue().set(
                "RECENT_LOGIN:"+ memberDto.getMemail(),"true",1, TimeUnit.DAYS
        );
        return token;
    }

    public MemberDto info(String token){
        //1.전달받은 token 으로 검증하기
        String memail = jwtUtil.validateToken(token);
        //2.검증이 실패이면 ' 비로그인중' 이거나 유효기간 만료. 실패
        if(memail == null) return  null;
        //3.검증이 성공이면 토큰에 저장된 이메일을 가지고 엔티티 조회
        MemberEntity memberEntity = memberEntityRepository.findByMemail(memail);
        //4.조회된 엔티티가 없으면 실패
        if(memberEntity == null)return null;
        //5.조회 성공시 조회된 엔티티들 dto로 반환하여 반환.
        return memberEntity.toDto();
    }

    //[4] 로그아웃
    public void logout(String token){
        //해당 토큰 이메일 조회
        String meamil = jwtUtil.validateToken(token);
        //조회된 이메일의 redis 토큰 삭제
        jwtUtil.deleteToken(meamil);
    }// f end

    @Autowired
    private final StringRedisTemplate stringRedisTemplate;

    //[5]최근 24시간 로그인 한 접속자 수
    public int loginCount(){
        //레디스에 저장된 키들 중에서 "RECENT_LOGIN으로 시작하는 모든 KEY 반환
        Set<String>keys = stringRedisTemplate.keys("RECENT_LOGIN:*");
        //반환된 개수 확인 , 비어있으면  0 아니면 size() 함수 이용한 key 개수 반환
        return keys == null?0: keys.size();
    }
}
