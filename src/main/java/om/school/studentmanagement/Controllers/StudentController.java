package om.school.studentmanagement.Controllers;

import om.school.studentmanagement.Exceptions.StudentNotFoundException;
import om.school.studentmanagement.Models.Student;
import om.school.studentmanagement.Services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/allStudents")
    public List<Student> AllStudents()
    {
        return studentService.getAllStudents();
    }

    // By Id
    @GetMapping("/student/{id}")
    public Student getStudent(@PathVariable int id)
    {
        try {
            return studentService.getStudentById(id);
        } catch (StudentNotFoundException e) {
            return Student.EMPTY;
        }
    }

    // By firstName
    @GetMapping("/student/firstName={firstName}")
    public List<Student> getStudentByFirstName(@PathVariable String firstName)
    {
        return studentService.getStudentsByFirstName(firstName);
    }

    // By email
    @GetMapping("/student/email={email}")
    public List<Student> getStudentsByEmail(@PathVariable String email)
    {
        return studentService.getStudentsByEmail(email);
    }


    @PostMapping("/addStudent")
    public String addStudent(@RequestBody Student student)
    {
        studentService.createStudent(student);
        return HttpStatus.OK.toString();
    }

    @PutMapping("updateStudent/{id}")
    public String updateStudent(@PathVariable("id") int id,
                                @RequestBody Student student)
    {
        studentService.updateStudent(id,student);
        return HttpStatus.OK.toString();
    }

    @DeleteMapping("/deleteStudent/{id}")
    public String deleteStudent(@PathVariable int id)
    {
        studentService.deleteStudentById(id);
        return HttpStatus.OK.toString();
    }

}
