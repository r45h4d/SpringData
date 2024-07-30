package az.ingress.service.abstraction;

import az.ingress.model.enums.StudentGender;
import az.ingress.model.request.AddOrUpdateStudentRequest;
import az.ingress.model.response.StudentResponse;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
@Service
public interface StudentService {
    void addStudent(AddOrUpdateStudentRequest request);
    StudentResponse getStudent(Long id);
    List<StudentResponse> getStudents();
    void deleteStudent(Long id);
    void updateStudent(Long id, AddOrUpdateStudentRequest request);
}
