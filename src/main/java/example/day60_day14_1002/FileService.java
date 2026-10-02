package example.day60_day14_1002;

import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

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
        try{
            multipartFile.transferTo( new File(uploadPath+fileName) );
            return fileName;
        } catch(Exception e){
            System.out.println(e);
            return null;
         }
        
    }


    // [3] 다운로드 함수
    // c드라이브-FileInput --> JAVA -ServletOut --> (servlet이용) 브라우저
    public void fileDownload(String fileName, HttpServletResponse response){
        // 1. 다운로드할 파일명과 HTTP 응답 객체 받기
        // 2. 다운로드 할 파일명과 업로드 경로 조합
        String downloadPath = uploadPath + fileName; // 업로드 경로 + 파일명
        // 3. 만약에 파일이 없으면 
        File file = new File( downloadPath ); 
        if( !file.exists() ){return ;}
        
        // 4. 있으면 파일 읽어오기, FileInputStream , + 예외처리  스캐너를 제외한 입출력은 예외처리가 뜸
        try{
            FileInputStream fin = new FileInputStream(downloadPath);
            long fileSize = file.length(); // 파일의 용량을 확인  long 바이트용량
            byte[] bytes = new byte[ (int)fileSize ];  // 파일 용량만큼 바이트 배열 생성
            fin.read( bytes ); // 파일 입력객체가 입력은 바이트들을 바이트배열에 저장
            fin.close(); // 스트림(이동)간 버퍼 안전하게 직접 닫기  // 남겨두면 쓰레기값이 계속 쌓이기 때문

        // 6. 다운로드 형식 지정 : 브라우저 마다 상이 ***
        // 실제 파일명으로 찾기 , UUID_짱구.jpg --> 짱구.jpg
        // .split("기준문자"); , 문자열내 특정 기준문자로 분해
            String realFileName = fileName.split("_")[1]; // 언더바 기준으로 쪼개서 2번째 인덱스 값 가져오기
            // HTTP 헤더에 다운로드 형식 지정,  한글 지원 X . URLEncoder.encode
            response.setHeader("Content-Disposition", "attachment;filename="+URLEncoder.encode(realFileName, "UTF-8"));

        // 5. 서버로 가져온 파일(바이트들) HTTP 응답 하기 out
            ServletOutputStream out = response.getOutputStream(); // 현재 다운로드요청한 서블릿의 출력 스트림 가져오기 // 요청한 서블릿 객체에 응답
            out.write( bytes ); // 서블릿출력스트림 객체로 앞전에 읽어온 파일 바이트 배열 내보내기
            out.close();
        }catch(Exception e){System.out.println(e);}
    }


    // [4] 파일 삭제 함수
    public boolean fileDelete(String fileName){
        // 1. 삭제할 파일명과 경로 조합
        String deleteFilePath = uploadPath+fileName;
        // 2. 만약에 경로에 파일이 존재하면 
        File file = new File(deleteFilePath);
        if(file.exists()){
            file.delete(); // 해당 경로에 파일 삭제함수
            return true;
        }else{return false;}
    }
    
}
