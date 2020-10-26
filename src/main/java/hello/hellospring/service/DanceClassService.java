package hello.hellospring.service;

import hello.hellospring.domain.DanceClass;
import hello.hellospring.domain.OhMember;
import hello.hellospring.repository.DanceClassRepository;
import hello.hellospring.repository.OhMemberRepository;
import hello.hellospring.util.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DanceClassService {

  private final DanceClassRepository danceClassRepository ;

  private final OhMemberRepository ohMemberRepository;


  /**
   * 강의등록
   * @param danceClass
   */
  public void insertClass (DanceClass danceClass) {
    OhMember ohMember = ohMemberRepository.findByUsername("dia1542").get();
    danceClass.setOhMember(ohMember);
    danceClassRepository.save(danceClass);
  }
//
//  /**
//   * 강의등록
//   * @param teaName
//   */
//  public List<DanceClass> selectClass (String teaName) {
//    return danceClassRepository.findByTeaName(teaName);
//  }

  /**
   * 강의등록
   * @param classId
   */
  public void deleteClass (Long classId) {
    danceClassRepository.deleteById(classId);
  }
}
