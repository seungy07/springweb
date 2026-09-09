package example.day45_day07_0909;

import java.util.Comparator;
import java.util.List;

public class Exam3 {
    public static void main(String[] args) {
        List<Integer> numbers = List.of(1,2,3,4,5,6,7,8,9,10);
        // 1. 컬렉션(List/Set/Map) 순회
            // 1) 일반 for문
            for(int index = 0; index <= numbers.size()-1; index++){
                System.out.println( numbers.get(index) );
            }
            // 2) 향산된 for문
            for( Integer data : numbers ){
                System.out.println( data );
            }
            // 3) forEach
            numbers.forEach( (data) -> { System.out.println( data );});

        // 2. 스트림API,  리스트객체.stream().중간연산1().중간연산2().최종연산();
            // 1) steram().forEach( (순회변수명) -> { 실행문 } ) , return 없는 단순 순회 제공 , 최종연산
            numbers.stream().forEach( (data) -> { System.out.println(data); } );
            // 2) steram().map( (순회변수명) -> { return 값; } ).toList();  , return 있는 순회 제동
            List<Integer> newlist = numbers.stream().map( ( data ) -> { return data; } ).toList();
            // 3) stream().filter( (순회변수명) -> { return 조건식; } ).최종연산(); 
            List<Integer> newList2 = numbers.stream().filter( (data) -> { return data % 2 == 0; } ).toList();
            System.out.println( newList2 ); // [2, 4, 6, 8, 10]
            // 4) stream().sorted().최종연산(); 오름차순 정렬 ,  Comparator.reverseOrder()  내림차순 정렬 
            List<Integer> newList3 = numbers.stream().sorted( Comparator.reverseOrder() ).toList();
            System.out.println( newList3 );  // [10, 9, 8, 7, 6, 5, 4, 3, 2, 1]
            // 5) stream().distinct().최종연산();  중복제거  ,stream().limit( 개수 ).toList();  개수 만큼 반환
            List<Integer> newList4 = numbers.stream().distinct().limit(3).toList();
            System.out.println( newList4 );
            // 6) 중간연산은 여러번 가능, 최종연산은 1번 가능 , builder() 패턴과 비슷
            List<Integer> newList5 = numbers.stream()
                                    .distinct() // 중복제거
                                    .filter( (x) -> { return x % 2 == 0 ;} )  // 조건식
                                    .map( (x) -> {return x;} )  // 반환식
                                    .sorted( Comparator.reverseOrder() ) // 정렬
                                    .limit(3)  //  나누기 
                                    .toList();   // 리스트로 반환 (최종연산)
            System.out.println(newList5);
            // *) JPA 에서 Entity <--> DTO 변환 과정

            // 스트림이란? 컬레션(리스트) 인스턴스를 흐름단위로 중간연산과 최종연산 제공 API(미리 만든 클래스/메소드)
            // 메소드 레퍼런스 API : 미리 만들어진 메소드들을 간편하게 호출하는 방법 제공 API 
            System.out.println( "메소드 호출 정석" );
            // (x) -> System.out::print; 람다식에서만 실행 가능
            
    }   
}
