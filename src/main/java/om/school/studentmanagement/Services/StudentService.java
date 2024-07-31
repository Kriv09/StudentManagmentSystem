package om.school.studentmanagement.Services;


import om.school.studentmanagement.Exceptions.StudentNotFoundException;
import om.school.studentmanagement.Models.Student;
import om.school.studentmanagement.Repositories.StudentRepository;
import om.school.studentmanagement.Utils.UtilsFunctions;
import org.aspectj.weaver.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private static final Logger log = LoggerFactory.getLogger(StudentService.class);
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentByFirstName(String firstName) throws StudentNotFoundException
    {
        return studentRepository.findByFirstName(firstName)
                .orElseThrow(
                        () -> new StudentNotFoundException("Student with firstName " + firstName + " not found")
                );
    }

    public Student getStudentByEmail(String email) throws StudentNotFoundException {
        return studentRepository.findByEmail(email)
                .orElseThrow(
                        () -> new StudentNotFoundException("Student with email " + email + " not found")
                );
    }


    public Student getStudentById(int id) throws StudentNotFoundException  {
        return studentRepository.findById(id)
                .orElseThrow(
                        () -> new StudentNotFoundException("Student with ID " + id + " not found")
                );
    }

    public void createStudent(Student student) {
        student.setPassword(UtilsFunctions.hashPassword(student.getPassword()));
        studentRepository.save(student);
    }

    public void updateStudent(int id,Student student) {
        Student oldStudent = null;
        try {
            oldStudent = this.getStudentById(id);
        } catch (StudentNotFoundException studentNotFoundException)
        {
            return;
        }

        oldStudent.setFirstName(UtilsFunctions.getOrDefault(student.getFirstName(), oldStudent.getFirstName()));
        oldStudent.setLastName(UtilsFunctions.getOrDefault(student.getLastName(), oldStudent.getLastName()));
        oldStudent.setEmail(UtilsFunctions.getOrDefault(student.getEmail(), oldStudent.getEmail()));
        oldStudent.setPassword(UtilsFunctions.getOrDefault(UtilsFunctions.hashPassword(student.getPassword()), oldStudent.getPassword()));

        studentRepository.save(oldStudent);
    }


    public void deleteStudentById(long id) {
        studentRepository.deleteById(id);
    }

}
