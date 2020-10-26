package hello.hellospring.service;

import hello.hellospring.repository.OhMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

  private final OhMemberRepository ohMemberRepository;

  @Autowired
  public MyUserDetailsService(OhMemberRepository ohMemberRepository) {
    this.ohMemberRepository = ohMemberRepository;
  }
  @Override
  public UserDetails loadUserByUsername (String username) throws UsernameNotFoundException {
   return ohMemberRepository.findByUsername(username).orElseThrow(()->new UsernameNotFoundException("사용자를 찾을 수 없습니다."));

  }
}
