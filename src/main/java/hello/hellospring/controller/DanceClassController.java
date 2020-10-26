package hello.hellospring.controller;

import hello.hellospring.domain.DanceClass;
import hello.hellospring.service.DanceClassService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class DanceClassController {

  private final DanceClassService danceClassService;

  @PutMapping("insert")
  public ResponseEntity<?> hello(DanceClass danceClass, Model model){
    HttpStatus responseStat;
    String resultMsg = "";
    try {
      danceClassService.insertClass(danceClass);
      resultMsg = "강의가 등록되었습니다.";
      responseStat = HttpStatus.OK;
    } catch (Exception e) {
      resultMsg = e.getMessage();
      responseStat = HttpStatus.BAD_REQUEST;
    }
    return new ResponseEntity<>(resultMsg, responseStat);
  }

//  @GetMapping("select")
//  public ResponseEntity<?> selectMyClassList(String teaName, Model model){
//    List<DanceClass> danceClassList = danceClassService.selectClass(teaName);
//    return new ResponseEntity<>(danceClassList, HttpStatus.OK);
//  }

  @DeleteMapping("delete")
  public ResponseEntity<?> deleteClass(Long classId){
  danceClassService.deleteClass(classId);
    return new ResponseEntity<>("강의가 삭제되었습니다.", HttpStatus.OK);
  }


}
