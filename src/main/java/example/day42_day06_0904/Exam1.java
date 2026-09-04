package example.day42_day06_0904;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

public class Exam1 {
    public static void main(String[] args) {
        // [1] 리터럴 / 상수 = 기본값
        int a = 3; // a 변수가 3 참조
        int b = 3; // b 변수가 3 참조
        // 두 변수가 참조하는 값은 총 몇개? 1개
        // [2] 참조란? 어떤한 값의 위치, 인스턴스(객체) 1개 당 참조 1개
        String c = new String("유재");
        String d = new String("강호동");
        // 두 변수가 참조하는 값은 2개
        Test t = new Test();
        t.name = "유재석";
        // t 변수가 참조하는 값은 총 몇개인가요? -> 1개(1개) , t -> Test 101번지 -> name 201번지

        // 자바 참조 
        // 1] '자유' 카테고리 등록
        Category c1 = new Category(1, "자유", new ArrayList<>() );
        // c1 참조 몇개 하고 있나요? 1개  c1 -> Category

        // 2] '자유' 카테고리에 게시물 작성
        Board b1 = new Board(1, "제목1", c1);
        // b1 참조 몇개? 1개 , b1 -> Board -> Category ,  String/기본타입 제외

        // *] b1을 통해 c1을 참조(알수) 할 수 있나요? 가능, 왜? board에 category 들어있으니?
        // *] JPA 개발자는 이 구조 --> db 연관관계(join) 사용 가능 . 단방향 참조
        // *] c1 통해 b1(참조)알 수 있나요? 불가능, category에 board 가 없기 때문 
        // *] JPA 개발자는 

        // 3] category에 board 넣어주자 (참조)
        c1.getList().add( b1 );
            // c1 -> category -> list( board )
            // *] c1 통해 b1 (참조)할 수 있나요? 가능
            // *] JPA 서로 참조 가능한 구조 : 양방향 참조 
        System.out.println( b1 );
            // b1 -> c1 -> b1 -> c1 -> b1 -> c1 ~~ 무한참조 <순환참조> 
            // 양방향쪽에 @ToString.Exclude 주입한다
            // toString( ) 이란? Object(슈퍼)클래스의 객체 주소값 반환 함수
            // + 오버라이딩: 객체 주소값 대신에 문자열로 반환 함수(주로)

            // 데이터베이스는 단방향: 참조[FK] 테이블에 PK 테이블의 PK 값 저장
            //  데이터베이스 양방향: X, 참조/매핑테이블 (실무적으로 권장x) join 때문
            // 결론: JPA 양방향, DB 양방향X
            // 실무에서는 양방향 비권장. 불피요한 자료들까지 불러오니까.
     

    }
}
@Data@AllArgsConstructor
class Board{
    private int bno;
    private String title;
    private Category category; // 참조FK
}
@Data@AllArgsConstructor
class Category{
    private int cno;
    private String cname;
    @ToString.Exclude // toString 사용 금지
    private List<Board> list = new ArrayList<>();
}

class Test{
    String name;
}
