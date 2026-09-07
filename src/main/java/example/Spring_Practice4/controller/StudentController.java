package example.Spring_Practice4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import example.Spring_Practice4.model.dto.StudentDto;
import example.Spring_Practice4.service.StudentSerivce;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController @RequestMapping("api/student")
public class StudentController {
    @Autowired 
    private StudentSerivce studentSerivce;

    @PostMapping("")
    public boolean 학생등록(@RequestBody StudentDto studentDto) {
        return studentSerivce.학생등록(studentDto);
    }

    // /api/student?studentId=1
    @DeleteMapping("")
    public  boolean 학생삭제(@RequestParam (name = "studentId") Integer studentId){
        return studentSerivce.학생삭제(studentId);
    }
    
    
}
