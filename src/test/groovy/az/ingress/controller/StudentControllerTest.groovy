package az.ingress.controller

import az.ingress.exception.ErrorHandler
import az.ingress.model.request.AddOrUpdateStudentRequest
import az.ingress.model.response.StudentResponse
import az.ingress.service.concrete.StudentServiceHandler
import com.fasterxml.jackson.databind.ObjectMapper
import io.github.benas.randombeans.EnhancedRandomBuilder
import io.github.benas.randombeans.api.EnhancedRandom
import org.springframework.http.HttpStatus
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import spock.lang.Specification

import static org.springframework.http.MediaType.APPLICATION_JSON
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*

class StudentControllerTest extends Specification {
    EnhancedRandom random = EnhancedRandomBuilder.aNewEnhancedRandom()
    private StudentServiceHandler studentService
    private MockMvc mockMvc

    void setup() {
        studentService = Mock()
        def studentController = new StudentController(studentService)
        mockMvc = MockMvcBuilders.standaloneSetup(studentController)
                .setControllerAdvice(new ErrorHandler())
                .build()
    }

    def "TestAddStudent success case"() {
        given:
        def url = "/v1/students"

        def request = random.nextObject(AddOrUpdateStudentRequest)

        when:
        def result = mockMvc.perform(post(url)
        .contentType(APPLICATION_JSON)
        .content(new ObjectMapper().writeValueAsString(request))
        ).andReturn()

        then:
        1 * studentService.addStudent(request)

        def response = result.response
        response.status == HttpStatus.CREATED.value()
    }

    def "TestGetStudent success case"(){
        given:
        def expectedResponse = random.nextObject(StudentResponse)
        def id = expectedResponse.id
        def url = "/v1/students/$id"


        when:
        def result = mockMvc.perform(get(url)).andReturn()

        then:
        1 * studentService.getStudent(id) >> expectedResponse

        def response = result.response
        response.status == HttpStatus.OK.value()
        response.contentAsString == new ObjectMapper().writeValueAsString(expectedResponse)
    }

    def "TestDeleteStudent success case"(){
        given:
        def id = random.nextObject(Long)
        def url = "/v1/students/$id"

        when:
        def result = mockMvc.perform(delete(url)).andReturn()

        then:
        1 * studentService.deleteStudent(id)

        def response = result.response
        response.status == HttpStatus.NO_CONTENT.value()
    }

    def "TestUpdateStudent success case"(){
        given:
        def request = random.nextObject(AddOrUpdateStudentRequest)
        def id = random.nextObject(Long)
        def url = "/v1/students/$id"

        when:
        def result = mockMvc.perform(patch(url)
                .contentType(APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(request))
        ).andReturn()

        then:
        1 * studentService.updateStudent(id, request)

        def response = result.response
        response.status == HttpStatus.NO_CONTENT.value()
    }
}
