package az.ingress.mapper

import az.ingress.dao.entity.StudentEntity
import az.ingress.model.request.AddOrUpdateStudentRequest
import io.github.benas.randombeans.EnhancedRandomBuilder
import io.github.benas.randombeans.api.EnhancedRandom
import spock.lang.Specification

class StudentMapperTest extends Specification{
    EnhancedRandom random = EnhancedRandomBuilder.aNewEnhancedRandom()
    def studentMapper = StudentMapper.STUDENT_MAPPER

    def "TestMapEntityToResponse success case"(){
        given:
        def studentEntity = random.nextObject(StudentEntity)

        when:
        def response = studentMapper.mapEntityToResponse(studentEntity)

        then:
        response.id == studentEntity.id
        response.name == studentEntity.name
        response.score == studentEntity.score
        response.gender == studentEntity.gender
    }

    def "TestBuildStudentEntity success case"(){
        given:
        def request = random.nextObject(AddOrUpdateStudentRequest)

        when:
        def response = studentMapper.buildStudentEntity(request)

        then:
        response.name == request.name
        response.score == request.score
        response.gender == request.gender
    }
}
