package az.ingress.mapper;

import az.ingress.dao.entity.StudentEntity;
import az.ingress.model.request.AddOrUpdateStudentRequest;
import az.ingress.model.response.StudentResponse;

public enum StudentMapper {
    STUDENT_MAPPER;

    public StudentEntity buildStudentEntity(AddOrUpdateStudentRequest request){
        return StudentEntity.builder().name(request.getName()).score(request.getScore()).gender(request.getGender()).build();
    }

    public StudentResponse mapEntityToResponse(StudentEntity student){
        return StudentResponse.builder()
                .id(student.getId())
                .name(student.getName())
                .score(student.getScore())
                .gender(student.getGender())
                .build();
    }
}