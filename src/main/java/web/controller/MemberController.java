package web.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import web.model.dto.MemberDto;
import web.service.MemberService;

@RestController
@RequestMapping("/member") //공통 url 정의
@RequiredArgsConstructor // final 필드의 생성자 생성
public class MemberController {
    // -> 관례적으로 다른곳에 해당하는 필드를 수정못하도록 final 사용한다.(안정성 보장)
    // -> 즉 final 사용시 @RequiredArgsConstructor 떄문에 @Autowired 안해도 된다.
    private final MemberService memberService;

    // [1] 회원가입 // { "memail" : "qwe@naver.com" , "mpwd" : "qwe" , "mname" : "유재석" }
    @PostMapping("/signup")
    public boolean signUp( @RequestBody MemberDto memberDto ){
        return memberService.signUp( memberDto );
    }
    // [2] 로그인
    @PostMapping("/login")
    public String login( @RequestBody MemberDto memberDto ){
        return memberService.login( memberDto );
    }

    //[3]로그인 된 회원 검증 / 내정보 조회
    @GetMapping("/info")
//    @RequestHeader : HTTP 헤더 정보를 매핑하는 어노테이션 JWT 정보는 HTTP 헤더에 담을 수 있음
        // Authorization : 인증 속성 , { Authorization : token 값 }
//    @RequestParam : HTTP 헤더의 경로 쿼리스트링 매핑하는 어노테이션
//    @RequestBody : HTTP 본문의 객체를 매핑하는 어노테이션
//    @PathVariable : HTTP 헤더의 경로 값 매핑하는 어노테이션
    public MemberDto info(@RequestHeader("Authorization")String token){
        //header: {'Authorization' : eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJxd2UxMjNAbmF2ZXIuY29tIiwiaWF0IjoxNzQ0NzcxODUwLCJleHAiOjE3NDQ4NTgyNTB9.UIgwKSxnRhs6Q34VuTq41hO-OmChylqQ6qdDyfyQYME}
        System.out.println(token);
        return memberService.info(token);
    }

    //[4]로그아웃
    @GetMapping("/logout")
    public void logout(
            @RequestHeader("Authorization")String token){
        memberService.logout(token);
    }

    //[5]최근 24시간 로그인 한 접속자 수
    @GetMapping("/login/count")
    public int loginCount(){
        return memberService.loginCount();
    }
}
