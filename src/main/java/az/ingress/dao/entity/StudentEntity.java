package az.ingress.dao.entity;

import az.ingress.model.enums.StudentGender;
import az.ingress.model.enums.StudentStatus;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

import static javax.persistence.EnumType.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="students")
@Builder
public class StudentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private BigDecimal score;

    @Enumerated(STRING)
    private StudentGender gender;

    @Enumerated(STRING)
    private StudentStatus status;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentEntity that = (StudentEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
