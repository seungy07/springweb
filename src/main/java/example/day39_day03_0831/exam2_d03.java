package example.day39_day03_0831;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

public class exam2_d03 {
    public static void main(String[] args) {
        Student s1 = new Student(); // 1. @NoArgsConstructor
        Student s2 = new Student("유재석",100,20); // 2. @AllArgsConstructor 
        s1.setKor(100); // 3. @Setter
        System.out.println( s2.getKor());// 4. @Getter
        System.out.println( s2.toString() );// 5. @ToString 
        // 6. 빌더 패턴을 이용한 객체 생성, 장점: 순서무관,선택적대입, 유연한객체생성
        Student s3 = Student.builder() // 빌더 패턴 시작
                    .kor(100)
                    .math(300)
                    .name("강호동")
                    .build(); // 빌드로 끝
        // 즉] new 생성자( ) [vs] 클래스명.builder( ).build( );   ㄴ선택
        // 클래스.메소드명( ),  메소드가 static [vs] 객체명.메소드명( ) 차이점: static
        System.out.println( s3 );
        Student.builder().kor(20).build();
        
    }
}
@NoArgsConstructor // 매개변수 없는 생성자 (자동)생성
@AllArgsConstructor // 전체 매개변수 있는 생성자 (자동) 생성
// @RequiredArgsConstructor // final 멤버변수의 생성자 (자동) 생성, final 써야 사용가능
@Getter @Setter // private 멤버변수의 getter/setter 메소드 (자동)생성
@ToString // 멤버변수 문자열 출력 메소드 (자동) 생성
@Data // setter + getter + RequiredArgsConstructor + ToString + @EqualsAndHashCode
@EqualsAndHashCode // 객체내 멤버변수의 값 모두 비교 메소드
@Builder // 빌더 패턴 지원
class Student{ 
    // 1. 멤버변수
    private String name;
    private int kor;
    private int math;
    // 2. 생성자 -> 롬복 어노테이션 대체
    // 3. getter, setter toString -> 롬복 어노테이션 대체

}
