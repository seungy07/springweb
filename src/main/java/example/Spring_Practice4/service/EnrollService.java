package example.Spring_Practice4.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import example.Spring_Practice4.model.dto.EnrollDto;
import example.Spring_Practice4.model.entity.CourseEntity;
import example.Spring_Practice4.model.entity.EnrollEntity;
import example.Spring_Practice4.model.entity.StudentEntity;
import example.Spring_Practice4.model.repository.CourseRepository;
import example.Spring_Practice4.model.repository.EnrollRepository;
import example.Spring_Practice4.model.repository.StudentRepository;

@Service 
public class EnrollService {
    @Autowired private EnrollRepository enrollRepository;
    @Autowired private CourseRepository courseRepository;
    @Autowired private StudentRepository studentRepository;

    //  수강등록 : Fk -> entity
    public boolean 수강등록(EnrollDto enrollDto){
        EnrollEntity enrollEntity = enrollDto.toEntity(); // FK 값은 여기서 entity로 안바뀌나?

        // DTO 내 FK값을 Entity 변환  
        Optional<StudentEntity> optional1 = studentRepository.findById(enrollDto.getStudentId());
        Optional<CourseEntity> optiona2 = courseRepository.findById(enrollDto.getCourseId());

        if(optional1.isPresent() && optiona2.isPresent()){  // FK 2개가 모두 엔티티가 존재하면
            // 학생엔티티 꺼내서 enroll 엔티티에 대입
            StudentEntity studentEntity = optional1.get();
            enrollEntity.setStudentEntity(studentEntity);

            // 과정엔티티 꺼내서 enroll 엔티티 대입
            CourseEntity courseEntity = optiona2.get();
            enrollEntity.setCourseEntity(courseEntity);

            // FK 엔티티 대입 후, save
            EnrollEntity saved = enrollRepository.save(enrollEntity);
            if(saved.getEnrollId() >= 1){ return  true;}
        }
        return false;
    }

    // 수강 조회
    public EnrollDto 수강조회(Integer enrollId){
        EnrollEntity enrollEntity = enrollRepository.findById((enrollId)).orElse(null);
        return  EnrollDto.from(enrollEntity);
    }
    
}
