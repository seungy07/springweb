package example.day60_day14_1002;

import java.io.File;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service 
public class FileService {
    // [1] 업로드 경로 설정  local 기준   ( 클라우드 업로드시 경로 수정 해야댐)
    // 1. 현재 프로젝트의 최상위 폴더 찾기
    private String baseDir = System.getProperty("user.dir");  // 현재 폴더 경로 반환
    // 2. 최상위 폴더 이후로 build 폴더로 업로드할 경로  ( build : (배포 후) 업로드 , src : 배포전으로 개발환경(업로드시 다시 배포해야함) )
    // * 일반사용자들은 업로드할 경우 개발자폴더가 아닌 톰캣(서버)폴더에 업로드
    // *추후에 AWS(클라우드) 경우 에는 클라우드 IP 
    private String uploadPath = baseDir+"/build/resources/main/static/upload/";

    // [2] 업로드 함수
    public String fileUpload(MultipartFile multipartFile){
        // 1.업로드할 파일의 MultipartFile 인터페이스 가져오기
        // 2. 만약에 업로드 파일이 없어면 취소
        if( multipartFile == null || multipartFile.isEmpty() ){return null;}

        // 3. 만약에 업로드 폴더가 없으면 폴더 최초 생성
        File dir = new File(uploadPath);  // 설정한 경로 File 객체 대입, [File객체] : 자바가 운영체제의 파일 조작 클래스
        if( !dir.exists() ){ dir.mkdir(); } // 설정한 경로의 폴더가 없으면 폴더 생성

        // 4. 업로드할 파일명이 중복 방지 --> 1) UUID  2) 업로드날짜/시간  3) PK 등등 식별 추가
        // --> 유재석/강호동이 서로 다른 파일의 같은 파일명 짱구.jpg 업로드한 경우에 다른 파일 취급하기 위해서      uuid_짱_구
        String fileName = UUID.randomUUID().toString()+"_"+multipartFile.getOriginalFilename() // uuid_짱구 _를 uuid 와 파일명을 구분 용도 ( 파일명의 _가 존재하면 안된다 )
                                                            .replaceAll("_", "-"); // uuid-짱구, // 기존 문자를 새로운 문자로 치환

        // 5. 업로드 처리 , .transferTo( 업로드할file 객체 );  예외 처리 발생
        try{multipartFile.transferTo( new File(uploadPath+fileName) );} catch(Exception e){System.out.println(e);}
        return null;
    }


    // [3] 다운로드 함수


    // [4] 파일 삭제 함수
    
}
