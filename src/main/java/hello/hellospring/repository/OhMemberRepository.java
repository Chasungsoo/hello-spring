package hello.hellospring.repository;

import hello.hellospring.domain.OhMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OhMemberRepository extends JpaRepository<OhMember,Long> {
  Optional<OhMember> findByUsername(String username);
  OhMember findByname(String username);
}
