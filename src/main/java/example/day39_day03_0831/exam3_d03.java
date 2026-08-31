package example.day39_day03_0831;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

public class exam3_d03 {
    public static void main(String[] args) {
        
        
    }
    
}
class SampleDao{ void method(){ System.out.println("메소드 실행 ");} }
// [1] 전통 방식의 객체 생성 ,  계속적인 dao 인스턴스 생성가능
class SampleController1{
    void method(){
        // 1. 다른 클래스내 메소드 호출하기
        SampleDao sampleDao = new SampleDao(); // 인스턴스생성(주체)
        sampleDao.method(); // 해당 인스턴스(주체)가 메소드 호출
    }
}
// [2] 전통 방식의 싱글톤 생성, dao인스턴스 생성 불가능
class SampleDao2{
    private SampleDao2(){}
    private static final SampleDao2 instance = new SampleDao2();
    public static SampleDao2 getIntance(){ return instance; }
    public void method(){}
}
class SampleController2{
    SampleDao2 dao2 = SampleDao2.getIntance();
    void method(){
        // 2. 다른 클래스내 메소드 호출하는 방법
        dao2.method();
    }
}

// [3] 스프링 IOC / DI
@Component
class SampleDao3{
    // 싱글톤 생략 -> @Component 이용하여 IOC(자동객체관리) 규칙으로 스프링 컨테이너에 자동 빈(객체) 등록
    void method(){}
}
class SampleController3{
    // 멤버변수의 쓴다
    @Autowired // 의존성 주입, 스프링 컨테이너에 등록된 빈(객체) 가져와서 대입 (사용할때)
    private SampleDao3 dao3;
    void method(){}
}
class SampleController4{
    // 2. 권장  
    private final SampleDao3 dao3;  // final 초기화 이후 수정불가능
    public SampleController4( SampleDao3 dao3){
        this.dao3 = dao3;
    }
    void method(){}
}
@RequiredArgsConstructor // final 멤버변수 생성자 (자동) 생성
class SampleController5{
    // 3. 롬복을 이용한 방식
    private final SampleDao3 dao3;

}