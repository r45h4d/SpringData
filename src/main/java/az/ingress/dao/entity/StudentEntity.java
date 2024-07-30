package az.ingress.dao.entity;

import az.ingress.model.enums.StudentGender;
import az.ingress.model.enums.StudentStatus;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of="id")
@Entity
@Table(name="students")
@Builder
public class StudentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private BigDecimal score;

    @Enumerated(EnumType.STRING)
    private StudentGender gender;

    @Enumerated(EnumType.STRING)
    private StudentStatus status;
}
