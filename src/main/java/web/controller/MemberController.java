package web.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member") //공통 url 정의
@RequiredArgsConstructor // final 필드의 생성자 생성
public class MemberController {

}
