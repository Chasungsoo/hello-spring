package hello.hellospring.domain;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "dance_class")
@Data
public class DanceClass {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "class_id")
  private Long id;

  @DateTimeFormat(pattern = "yyyyMMddHHmmss")
  private LocalDateTime startTime;

  @DateTimeFormat(pattern = "yyyyMMddHHmmss")
  private LocalDateTime endTime;

  @Enumerated(EnumType.STRING)
  private ClassType classType;

  @Enumerated(EnumType.STRING)
  private ClassRoom classRoom;

}
