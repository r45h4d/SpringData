package az.ingress.model.request;

import az.ingress.model.enums.StudentGender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddOrUpdateStudentRequest {
    private String name;
    private BigDecimal score;
    private StudentGender gender;
}
