package om.school.studentmanagement.Repositories;

import om.school.studentmanagement.Models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {
    @Query("SELECT S FROM Student S")
    List<Student> findAll();


    @Query("SELECT s FROM Student s WHERE s.Id = :id")
    Optional<Student> findById(@Param("id") int id);

    Student save(Student student);

    void deleteById(long id);

    List<Student> findByFirstName(String firstName);

    List<Student> findByEmail(String email);
}
