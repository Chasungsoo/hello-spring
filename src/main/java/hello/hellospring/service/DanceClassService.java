package hello.hellospring.service;

import hello.hellospring.domain.ClassMember;
import hello.hellospring.domain.DanceClass;
import hello.hellospring.domain.OhMember;
import hello.hellospring.repository.ClassMemberRepository;
import hello.hellospring.repository.DanceClassRepository;
import hello.hellospring.repository.OhMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DanceClassService {

  private final DanceClassRepository danceClassRepository ;

  private final OhMemberRepository ohMemberRepository;

  private final ClassMemberRepository classMemberRepository;


  /**
   * 강의등록
   * @param danceClass
   */
  @Transactional
  public void insertClass (DanceClass danceClass, String memberId) {

    List<ClassMember> classMembers = new ArrayList<>();
    danceClassRepository.save(danceClass);
    String[] arrayMember = memberId.split(",",-1);

    for (String m: arrayMember ) {
      ClassMember classMember = new ClassMember();
      OhMember ohMember = ohMemberRepository.findByUsername(m).get();
      classMember.setDanceClass(danceClass);
      classMember.setOhMember(ohMember);
      classMembers.add(classMember);
    }
    classMemberRepository.saveAll(classMembers);

  }

  /**
   * 해당 날짜 강의 조회
   * @param date
   */
  public List<DanceClass> findList (LocalDateTime date) {
      return danceClassRepository.findAllByStartTimeBetween(date,date.plusDays(1L));
  }

  /**
   * 강의삭제
   * @param classMember
   */
  @Transactional
  public void deleteClass (ClassMember classMember) {
    classMemberRepository.deleteByDanceClass_Id(classMember.getDanceClass().getId());
    danceClassRepository.deleteById(classMember.getDanceClass().getId());
  }
}
