package hello.hellospring.repository;

import hello.hellospring.domain.ClassMember;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ClassMemberRepository extends JpaRepository<ClassMember,Long> {
  void deleteByDanceClass_Id(Long id);
}