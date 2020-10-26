package hello.hellospring.service;

import hello.hellospring.domain.OhMember;
import hello.hellospring.repository.OhMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class AuthService {

  private OhMemberRepository ohMemberRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  public AuthService(OhMemberRepository ohMemberRepository) {
    this.ohMemberRepository = ohMemberRepository;
  }

  public void signUpUser(OhMember ohMember) {
    ohMember.setPassword(passwordEncoder.encode(ohMember.getPassword()));
    ohMember.setRoles(Collections.singletonList("ROLE_USER"));
    ohMemberRepository.save(ohMember);
  }

  public OhMember loginUser(String id , String password) throws Exception {

     OhMember ohMember = ohMemberRepository.findByUsername(id).orElseThrow(()->  new Exception("존재하지 않는 아이디 입니다."));
    if (ohMember.getPassword().equals(passwordEncoder.encode(password))) throw new Exception("비밀번호가 틀립니다.");
    return ohMember;
  }
}
