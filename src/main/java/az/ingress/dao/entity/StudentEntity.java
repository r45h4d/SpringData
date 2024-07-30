package az.ingress.dao.entity;

import az.ingress.model.enums.StudentGender;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of="id")
@Entity
@Table(name="students")
public class StudentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private BigDecimal score;

    @Enumerated(EnumType.STRING)
    private StudentGender gender;
}
