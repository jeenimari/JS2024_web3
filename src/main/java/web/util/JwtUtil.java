package web.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import java.security.Key;
import java.util.Date;

@Component // SPring 컨테이너에 빈 등록

public class JwtUtil {

//    private String secretKey = " 인코딩된 HS512알고리즘"
    //private String secretKey = "qP9sLxV3tRzWn8vMbKjUyHdGcTfEeXcZwAoLpNjMqRsTuVyBxCmZkYhGjFlDnEpQzFgXt9pMwX8Sx7CtQ5VtBvKmA2QwE3D";

    //두번째 방법  라이브러리를 사용한 임의 키
    private Key secreKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    //[1]JWT 토큰 발급 , 사용자의 이메일을 받아서 토큰 만들기
    public String createToken(String memail){
        return Jwts.builder()
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
            return claims.getSubject();
        }catch (ExpiredJwtException e){
            //토큰 이 만료 되었을때 예외 클래스
            System.out.println(">>JWT 토큰 기한 만료: " + e);
        }catch (JwtException e){
//            그 외 모든 토큰 예외 클래스
            System.out.println(">>JWT 예외 : " + e);
        }
        return null; //유효하지 않은 토큰 또는 오류 발생시 null 반환
    }

}
