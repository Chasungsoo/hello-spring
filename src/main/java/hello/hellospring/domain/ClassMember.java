package hello.hellospring.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "class_member")
public class ClassMember {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "cm_id")
  private Long id;

  @ManyToOne
  @JoinColumn(name = "class_id")
  private DanceClass danceClass;

  @ManyToOne
  @JoinColumn(name = "member_id")
  private OhMember ohMember;

}
