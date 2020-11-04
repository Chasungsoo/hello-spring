package hello.hellospring.repository;

import hello.hellospring.domain.DanceClass;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;

public interface DanceClassRepository extends JpaRepository<DanceClass,Long> {
  List<DanceClass> findAllByStartTimeBetween(LocalDateTime startTime,LocalDateTime endTime);

}
