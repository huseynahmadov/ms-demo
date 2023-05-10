package kubernetes.service;

import kubernetes.dto.StudentResponse;
import kubernetes.dto.request.CreateStudentRequest;
import kubernetes.dto.request.UpdateStudentRequest;
import kubernetes.exception.StudentNotFoundException;
import kubernetes.mapper.StudentMapper;
import kubernetes.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    public void createStudent(CreateStudentRequest request) {
        studentRepository.save(studentMapper.mapStudentRequestToStudent(request));
        log.info("Student created: {}", request);
    }

    public List<StudentResponse> getAllStudents() {
        var students = studentRepository.findAll()
                .stream()
                .map(studentMapper::mapStudentToStudentResponse)
                .collect(Collectors.toList());

        log.info("All students: {}", students);
        return students;
    }

    public StudentResponse getStudent(Long id) {
        var student = studentMapper.mapStudentToStudentResponse(studentRepository.findById(id)
                .orElseThrow(() -> exStudentNotFound(id)));

        log.info("Student data received: {}", student);
        return student;
    }

    public void updateStudent(Long id, UpdateStudentRequest request) {
        var student = studentRepository.findById(id)
                .orElseThrow(() -> exStudentNotFound(id));

        studentMapper.mapUpdateRequestToEntity(student, request);

        studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        studentRepository.findById(id)
                .orElseThrow(() -> exStudentNotFound(id));
        studentRepository.deleteById(id);
        log.info("Student deleted: {}", id);
    }

    private StudentNotFoundException exStudentNotFound(Long id) {
        throw new StudentNotFoundException("Student not found " + id);
    }

}
