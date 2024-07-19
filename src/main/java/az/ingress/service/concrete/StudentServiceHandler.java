package az.ingress.service.concrete;

import az.ingress.dao.repository.StudentRepository;
import az.ingress.model.request.AddOrUpdateStudentRequest;
import az.ingress.model.response.StudentResponse;
import az.ingress.service.abstraction.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class StudentServiceHandler implements StudentService {
    private final StudentRepository studentRepository;
    @Override
    public void addStudent(AddOrUpdateStudentRequest request) {

    }

    @Override
    public StudentResponse getStudent(Long id) {
        return null;
    }

    @Override
    public List<StudentResponse> getStudents() {
        return null;
    }

    @Override
    public void deleteStudent(Long id) {

    }

    @Override
    public void updateStudent(Long id, AddOrUpdateStudentRequest request) {

    }
}
