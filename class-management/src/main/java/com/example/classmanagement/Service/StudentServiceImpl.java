package com.example.classmanagement.Service;

import com.example.classmanagement.dto.StudentDTO;
import com.example.classmanagement.dto.UpdateStudentRequest;
import com.example.classmanagement.entity.Student;
import com.example.classmanagement.exception.NotFoundException;
import com.example.classmanagement.mapper.StudentDTOMapper;
import com.example.classmanagement.repository.ClazzRepository;
import com.example.classmanagement.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService{

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ClazzRepository clazzRepository;

    //3. Thực hiện cập nhật thông tin 1b học viên dựa vào Id
    @Override
    public StudentDTO updateStudent(Integer studentId, UpdateStudentRequest request) {
        Student student2Update = studentRepository.findById(studentId).get();
        if (student2Update==null) {
            throw new NotFoundException("Student id "+ studentId + " not found");
        }
        //xem lại
        student2Update = Student.builder()
                .id(studentId)
                .name(request.getName()!=null? request.getName() : student2Update.getName())
                .age(request.getAge()!=0? request.getAge() : student2Update.getAge())
                .ranking(request.getRanking()!=null? request.getRanking() : student2Update.getRanking())
                .clazz(request.getClazzId()!=0?
                        student2Update.getClazz():
                        clazzRepository.findById(request.getClazzId()).orElseThrow())
                .build();
        studentRepository.save(student2Update);
        return StudentDTOMapper.toStudentDTO(student2Update);
    }

    //4. Có 1 bạn xin nghỉ học, hãy giúp thầy xóa bạn đó khỏi lớp học
    //Reset lại clazz, vẫn giữ data lại trong studentRepo
    @Override
    public void deleteStudent(Integer studentId){
        Student student2Delete = studentRepository.findById(studentId)
                .orElseThrow();
        student2Delete.setClazz(null);
        studentRepository.save(student2Delete);
    }
}
