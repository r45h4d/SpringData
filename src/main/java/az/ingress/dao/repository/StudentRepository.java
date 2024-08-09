package az.ingress.dao.repository;

import az.ingress.dao.entity.StudentEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
@Repository
public interface StudentRepository extends CrudRepository<StudentEntity, Long> {
    @Override
    List<StudentEntity> findAll();

    @Query(nativeQuery = true, value = "SELECT * FROM students WHERE status =:status")
    List<StudentEntity> getByStatus(String status);

    @Query("SELECT s FROM StudentEntity s WHERE s.score >= :score")
    List<StudentEntity> getByScore(BigDecimal score);

}