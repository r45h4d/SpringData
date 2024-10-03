package az.ingress.controller;

import az.ingress.model.request.AddOrUpdateStudentRequest;
import az.ingress.model.response.StudentResponse;
import az.ingress.service.abstraction.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/students")
public class StudentController {
    private final StudentService studentService;

    @PostMapping
    @ResponseStatus(CREATED)
    public void addStudent(@RequestBody AddOrUpdateStudentRequest request){
        studentService.addStudent(request);
    }

    @GetMapping("/{id}")
    @ResponseStatus(OK)
    public StudentResponse getStudent(@PathVariable Long id){
        return studentService.getStudent(id);
    }

    @GetMapping
    @ResponseStatus(OK)
    public List<StudentResponse> getStudents(){
        return studentService.getStudents();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void deleteStudent(@PathVariable Long id){
        studentService.deleteStudent(id);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void updateStudent(@PathVariable Long id,
                               @RequestBody AddOrUpdateStudentRequest request){
        studentService.updateStudent(id, request);
    }
}
