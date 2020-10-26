package hello.hellospring.repository;

import hello.hellospring.domain.OhMember;
import hello.hellospring.service.AuthService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class DanceClassRepositoryTest {

  private AuthService authService;

  @Autowired
  public DanceClassRepositoryTest(AuthService authService) {
    this.authService = authService;
  }

  @Test
  public void signUpUser() {

    OhMember ohMember = new OhMember();
    ohMember = ohMember.builder()
        .username("dia1542")
        .password("asd622539")
        .name("차성수")
        .email("dia1542@naver.com")
        .telNo("01031822041")
        .address("서울특별시 천왕역 새롬스타빌")
        .build();
    authService.signUpUser(ohMember);
  }

  @Test
  public void login() throws Exception {

    authService.loginUser("dia1542","asd622539");
  }

}
