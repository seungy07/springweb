package example.Spring_Practice4.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import example.Spring_Practice4.model.dto.StudentDto;
import example.Spring_Practice4.model.entity.StudentEntity;
import example.Spring_Practice4.model.repository.StudentRepository;

@Service 
public class StudentSerivce {
    @Autowired 
    private StudentRepository studentRepository;

    // 학생등록
    public boolean 학생등록( StudentDto studentDto){
        StudentEntity studentEntity = studentDto.toEntity();
        StudentEntity saved = studentRepository.save(studentEntity);
        if( saved.getStudentId() >= 1){ return  true;} return false;
    }

    // 학생삭제 : PK가 삭제될 때 연관데 FK가 존재 하면 제약조건 확인
    // JPA : cascade = CascadeType.REMOVE,  SQL : ON delete cascade
    public boolean 학생삭제( Integer studentId ){
        // 학생번호를 이용한 학생 엔티티 찾기
        Optional<StudentEntity> optional = studentRepository.findById( studentId );
        if(optional.isPresent()){return  true; } 
        return false;
    }
    
}
