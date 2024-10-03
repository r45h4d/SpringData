package az.ingress.service

import az.ingress.dao.entity.StudentEntity
import az.ingress.dao.repository.StudentRepository
import az.ingress.model.enums.StudentStatus
import az.ingress.service.concrete.StudentServiceHandler
import io.github.benas.randombeans.EnhancedRandomBuilder
import io.github.benas.randombeans.api.EnhancedRandom
import spock.lang.Specification

class StudentServiceHandlerTest extends Specification{
    EnhancedRandom random = EnhancedRandomBuilder.aNewEnhancedRandom()
    StudentServiceHandler studentService
    StudentRepository studentRepository

    def setup(){
        studentRepository = Mock()
        studentService = new StudentServiceHandler(studentRepository)
    }

    def "TestUpdateStudentStatus success case"(){
        given:
        def id = random.nextObject(Long)
        def status = StudentStatus.DELETED
        def entity = random.nextObject(StudentEntity)
        entity.status = StudentStatus.ACTIVE

        when:
        studentService.updateStudentStatus(id, status)

        then:
        1 * studentRepository.findById(id) >> Optional.of(entity)
        1 * studentRepository.save(entity)
        entity.status == status
    }
}
