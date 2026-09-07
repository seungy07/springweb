package example.Spring_Practice4.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import example.Spring_Practice4.model.dto.CourseDto;
import example.Spring_Practice4.model.dto.StudentDto;
import example.Spring_Practice4.model.entity.CourseEntity;
import example.Spring_Practice4.model.repository.CourseRepository;

@Service 
public class CourseService {
    @Autowired 
    private CourseRepository courseRepository;

    // 등록
    public boolean 과정등록(CourseDto courseDto){
        CourseEntity courseEntity = courseDto.toEntity(); // dto -> entity
        CourseEntity saved = courseRepository.save(courseEntity);
        if(saved.getCourseId() >= 1){ return  true; } return false;   
    }

    // 전체조회
    public  List<CourseDto> 과정전체조회(){
        List<CourseEntity> courseEntities = courseRepository.findAll();

        // entity -> dto
        List<CourseDto> courseDtos = new ArrayList<>();
        courseEntities.forEach( (courseEntity ) -> {
            CourseDto courseDto = CourseDto.from(courseEntity); // 하나씩 entity -> dto
            
            // 과정 Dto에 학생목록 추가****
            // 현재 과정(Course) -> 수강기록(enroll)들을 반복 -> 수강기록 -> 학생(student)
            courseEntity.getEnrollEntities().forEach( (enroll) -> {
                StudentDto studentDto = StudentDto.from( enroll.getStudentEntity());
                courseDto.getStuedetDtos().add(studentDto);
            }); 
            courseDtos.add(courseDto);
        });
        return  courseDtos;
    }
    
}
