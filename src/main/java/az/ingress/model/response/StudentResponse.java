package az.ingress.model.response;

import az.ingress.model.enums.StudentGender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentResponse {
    private Long id;
    private String name;
    private BigDecimal score;
    private StudentGender gender;
}
