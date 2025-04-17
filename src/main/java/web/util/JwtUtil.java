package web.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import java.security.Key;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component // SPring 컨테이너에 빈 등록

public class JwtUtil {

//    private String secretKey = " 인코딩된 HS512알고리즘"
    //private String secretKey = "qP9sLxV3tRzWn8vMbKjUyHdGcTfEeXcZwAoLpNjMqRsTuVyBxCmZkYhGjFlDnEpQzFgXt9pMwX8Sx7CtQ5VtBvKmA2QwE3D";

    //두번째 방법  라이브러리를 사용한 임의 키
    private Key secreKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    @Autowired // 빈 주입
    private StringRedisTemplate stringRedisTemplate;  //Redis 조작하기 위한 객체 con ps rs 와 비슷 JSP<----

    //[1]JWT 토큰 발급 , 사용자의 이메일을 받아서 토큰 만들기
    public String createToken(String memail){
        String token = Jwts.builder()
                //토큰에 넣을 내용물 , 로그인 성공한 회원의 이메일을 넣는다.
                .setSubject(memail)
                //토큰이 발급된 날짜 new Date():자바에서 제공하는 현재 날짜 클래스
                .setIssuedAt(new Date())
                // 토큰 만료 시간  1000 x 초 x 분 x 시
                .setExpiration(new Date(System.currentTimeMillis()+(1000 * 60 * 60 * 24) ) ) // 24시간동안 토큰유지
                //지정한 비밀키로 암호화 한다
                .signWith(secreKey)
                // 위정보로 JWT 토큰 생성하고 반환한다.
                .compact();
        //+ 중복 로그인 방지 하고자 웹서버가 아닌 Redis 에 토큰 저장
        //(1) Redis에 토큰 저장하기 . opsForValue(key,value); ,.opsForValue(식,토큰);
        stringRedisTemplate.opsForValue().set("JWT:"+memail,token ,24    , TimeUnit.HOURS);
        //(2) Redis에 저장된 Key 들을 확인 .keys("*") : 현재 Redis 의 저장된 모든 key반환
        System.out.println(stringRedisTemplate.keys("*"));
        //(3) Redis 에 저장된 key값 확인 .opsForValue()get(key);
        System.out.println(stringRedisTemplate.opsForValue().get("JWT:"+memail));
        return token;
    }// f end

    //[2]JWT 토큰 검증
    public String validateToken(String token){
        try{
            Claims claims = Jwts.parser() //parser() : JWT 토큰 검증하기 위한 함수
                    .setSigningKey(secreKey) //검증하기 위한 비밀키 넣기.
                    .build() //검증 실행 , 검증 실패시 예외 발생
                    .parseClaimsJws(token)// 검증할 토큰 해석 , 실패시 예외 발생
                    .getBody(); //검증된 claims 객체 생성

            //claims 안에는 다양한 토큰번호가 들어있다.
            System.out.println(claims.getSubject());

            //+중복 로그인 방지하고자 Redis 에서 최근 로그인된 토큰 확인
            String memail = claims.getSubject(); // 현재 전달받은 토큰의 저장된 회원정보(이메일)
            //1.레디스에서 최신 토큰 가져오기
            String redisToken = stringRedisTemplate.opsForValue().get("JWT:"+memail);
            if(token.equals(redisToken)){return memail;} // 현재 로그인상태 정상(중복 로그인이 아니다.)
            //(3) 만약에 두 토큰이 다르면 null이 리턴된다 . (토큰 불일치 또는 중복 로그인 감지)
            else{
                System.out.println(">>>중복 로그인 감지");
            }

        }catch (ExpiredJwtException e){
            //토큰 이 만료 되었을때 예외 클래스
            System.out.println(">>JWT 토큰 기한 만료: " + e);
        }catch (JwtException e){
//            그 외 모든 토큰 예외 클래스
            System.out.println(">>JWT 예외 : " + e);
        }
        return null; //유효하지 않은 토큰 또는 오류 발생시 null 반환
    }

    //[3]로그아웃시 redis에 저장된 토큰 삭제 서비스
    public void deleteToken(String memail){
        stringRedisTemplate.delete("JWT:" + memail);
    }

}
