package hello.hellospring.controller;

import hello.hellospring.domain.ClassMember;
import hello.hellospring.domain.DanceClass;
import hello.hellospring.service.DanceClassService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@CrossOrigin(origins = "*",allowedHeaders = "*")
@RestController
@RequiredArgsConstructor
public class DanceClassController {

  private final DanceClassService danceClassService;

  @PutMapping("insert")
  public ResponseEntity<?> hello(DanceClass danceClass,String memberId, Model model){
    HttpStatus responseStat;
    String resultMsg = "";
    try {
      danceClassService.insertClass(danceClass , memberId);
      resultMsg = "강의가 등록되었습니다.";
      responseStat = HttpStatus.OK;
    } catch (Exception e) {
      resultMsg = e.getMessage();
      responseStat = HttpStatus.BAD_REQUEST;
    }
    return new ResponseEntity<>(resultMsg, responseStat);
  }

  @GetMapping("/select")
  public ResponseEntity<?> selectClassList(String dateTime){
    return new ResponseEntity<>(
        danceClassService.findList( LocalDateTime.parse( dateTime,DateTimeFormatter.ofPattern( "yyyy-MM-dd HH:mm:ss" ) ) ), HttpStatus.OK);
  }

  @DeleteMapping("delete")
  public ResponseEntity<?> deleteClass(ClassMember classMember){
  danceClassService.deleteClass(classMember);
    return new ResponseEntity<>("강의가 삭제되었습니다.", HttpStatus.OK);
  }


}
