package hello.hellospring.controller;

import hello.hellospring.domain.OhMember;
import hello.hellospring.repository.OhMemberRepository;
import hello.hellospring.service.AuthService;
import hello.hellospring.model.Response;
import hello.hellospring.util.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin(origins = "*",allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class OhDanceMemeberController {

  private AuthService authService;

  @Autowired
  private JwtTokenProvider jwtTokenProvider;

  @Autowired
  public OhDanceMemeberController(AuthService authService) {
    this.authService = authService;
  }

  @PostMapping("/ip")
  public ResponseEntity<String> ip (HttpServletRequest request) {
    // 요청을 보낸 클라이언트의 IP주소를 반환합니다.
    return ResponseEntity.ok(request.getRemoteAddr());
  }

  @PostMapping("/signup")
  public Response signUpUser(@RequestBody OhMember ohMember) {
    Response response = new Response();
    try{
      authService.signUpUser(ohMember);
      response.setMessage("회원가입을 성공적으로 완료했습니다.");
    }catch (Exception e) {
      response.setMessage("회원가입을 하는 도중 오류가 발생했씁니다.");
    }
    return response;
  }
  // 로그인
  @PostMapping("/login")
  public String login(@RequestBody OhMember ohMember) throws Exception {
    ohMember = authService.loginUser(ohMember.getUsername(),ohMember.getPassword());
    return jwtTokenProvider.createToken(ohMember.getUsername(), ohMember.getUserRole().name());
  }
}
