package az.ingress.service.concrete;

import az.ingress.dao.entity.StudentEntity;
import az.ingress.dao.repository.StudentRepository;
import az.ingress.exception.NotFoundException;
import az.ingress.model.request.AddOrUpdateStudentRequest;
import az.ingress.model.response.StudentResponse;
import az.ingress.service.abstraction.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.List;

import static az.ingress.mapper.StudentMapper.STUDENT_MAPPER;
import static az.ingress.model.enums.ExceptionConstants.ORDER_NOT_FOUND;
import static az.ingress.model.enums.StudentStatus.DELETED;
import static org.springframework.transaction.annotation.Isolation.READ_COMMITTED;
import static org.springframework.transaction.annotation.Propagation.REQUIRED;
import static org.springframework.transaction.annotation.Propagation.REQUIRES_NEW;

@Service
@RequiredArgsConstructor
@Slf4j
public class StudentServiceHandler implements StudentService {
    private final StudentRepository studentRepository;
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void addStudent(AddOrUpdateStudentRequest request) {
        studentRepository.save(STUDENT_MAPPER.buildStudentEntity(request));
    }

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    @Override
    public StudentResponse getStudent(Long id) {
        log.info("ActionLog.getStudent.start id:{}",id);
        var student = fetchStudentIfExist(id);
        log.info("ActionLog.getStudent.success id:{}",id);
        return STUDENT_MAPPER.mapEntityToResponse(student);
    }

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    @Override
    public List<StudentResponse> getStudents() {
        return studentRepository.findAll().stream().map(STUDENT_MAPPER::mapEntityToResponse).toList();
    }

    @Transactional(propagation = REQUIRES_NEW, rollbackFor = Exception.class)
    @Override
    public void deleteStudent(Long id) {
        var student = fetchStudentIfExist(id);
        student.setStatus(DELETED);
        studentRepository.save(student);
    }

    @Transactional(propagation = REQUIRED, isolation = READ_COMMITTED, rollbackFor = Exception.class)
    @Override
    public void updateStudent(Long id, AddOrUpdateStudentRequest request) {
        var student = fetchStudentIfExist(id);
        student.setScore(student.getScore());
        student.setName(student.getName());
        studentRepository.save(student);
    }

    @PostConstruct
    private void test(){
        // var students = studentRepository.getByScore(new BigDecimal(6));
        var students = studentRepository.getByStatus(DELETED.name());
        students.forEach(it->
                System.out.println("STUDENT_ID : "+it.getId()));
    }

    private StudentEntity fetchStudentIfExist(Long id){
       return studentRepository.findById(id).orElseThrow(
               ()->{
                   log.error("ActionLog.fetchStudentIfExist.error student with id:{} not found",id);
                   return new NotFoundException(ORDER_NOT_FOUND.getCode(), ORDER_NOT_FOUND.getMessage());
               }
       );
    }
}
