// .js 파일은 .html파일 내 <script src="index.js"></script>
// 즉] js 코드는 html내 포함된다.

// 1. 변수와 상수 선언 
let count = 10; // 변수선언 키워드 let
    count = 3; // 변수는 수정 가능
const const1 = 10; // 상수선언 키워드 const
    // const1 = 3; // 상수는 수정 불가능 , 오류코드

// 2. 문자열 템플릿,  ` 빽틱
console.log( `hello ${count} + ${const1}` );
let html = `<div> hello ${count} </div>`;  console.log(html);
document.querySelector("body").innerHTML = html;

// 3. 조건문, if
const point = 85;
if(point >= 90){ console.log("A");} else if( point >= 80){console.log("B");}else{console.log("졸림")}
// 3-2 : 삼항연산자
point > 90 ? console.log("A") : console.log("졸림")
// 3-3 : 단축평가 , 조건 && 참이면 결과 , 조건 || 거짓이면 결과 
console.log( point >= 90 && 'A학점' );  // 만약에 90학점 이상이면 'A학점' 아니면 false
console.log( point >= 90 || 'A');  // 만약에 90점이상이면 true 아니면 A 출력
// 3-4 : null 체크 연산자, 조건 ?? null이면 결과
const nickName = null;
console.log( nickName ?? '익명사용자' );

// 4. 반복문
const array = [ 10, 20, 30, 40, 50]
for( let i = 0 ; i <= array.length -1 ; i++){console.log(array[i])}
// 4-2 : 향상된 for문, 반복변수명 in 배열명 , 반복변수명 of 배열명
for( let i in array ){ console.log(array[i])} // in 인덱스 하나씩 순회
for(let value of array ){ console.log(value)} // of 값 하나씩 조회
// 4-3 : forEach 순회, map 순회+반환, filter 순회+조건(논리)
array.forEach( (value) => { console.log(value)} ) // 자바: ->  JS : =>
const newArray = array.map( (value) =>{ return value;} ); // forEach 반환 없고 map 반환있음
console.log(newArray);
const newArray2 = array.filter( (value) => {return value >= 20 ; } ) // fillter 조건에 따른 반환
console.log(newArray2)

// 5. 함수 : 
// 5-1 : 선언적 함수
function func1( 매개변수1, 매개변수2 ){ }
// 5-2 : 익명(이름없는) 함수, 주로 변수/상수에 저장 사용
const func2 = function( 매개변수1, 매개변수2 ){ }
// 5-3 : 화살표/람다식 함수, 주로 변수/상수에 저장 사용
const func3 = ( 매개변수1, 매개변수2 ) => { }
// 5-4 : 매개변수에 기본값 대입가능, 만일 인수가 없을 때 기본값 대입
const func4 = (매개변수1, 매개변수2, 매개변수3 = 'student') => { console.log(매개변수3) }
// ---- 함수 호출
func1(4,10);
func2(4,10); // 익명 또는 람다 함수는 변수명/상수명 호출
func3(10, {name : "유재석"})
func4(10,4); // 만일 인수가 없는 매개변수는 기본값 대입된다

// 6. 객체 : 여러개 값을 가진 하나의 값 , 주로 변수/상수 저장
// 6-1 : 속성명(key) : 속성값(value) , 값에는 객체/배열/함수 저장 가능 
const obj1 = { name : "유재석" , age : 20 , func1 : (param) => { } }
console.log( obj1.age )  // 속성명으로 속성값 호출
// 6-2 : [ 값, 값 , 값] 
const obj2 = [ '유재석' , 40 , (parm) =>{ } ]
console.log( obj2[1] ) // 인덱스로 속성값 호출


// 7. 스프레드 연산자 : ...배열 또는 객체 복사할 때 사용, 사용처: 주소값 변경 목적 ( 리액트/플러터 )
const obj3 = { ...obj1 ,  phone : "010" } // { ...기존객체, 새로운속성 }
console.log( obj3 )
const obj4 = [ "010", ...obj2 ] // [ ...기존배열, 새로운값 ]
console.log(obj4)

// 8. 구조분해 할당: 배열 또는 객체에서 값을 분해해서 각 변수/상수에 저장
const { name, age } = obj1 // 오른쪽 객체내 왼쪽에 각 속성값들을 변수/상수에 값 대입
console.log( name ); console.log(age);
const [ name2, ...array2 ] = obj2;  // 오른쪽 배열내 순서대로 값들을 변수/상수에 대입, ...나머지들을
console.log(name2)
console.log(array2) // 나머지(그외)

// 9. 콜백함수 : 함수 전달하여 나중에 전달받은 함수 실행, 
function printSucces( message ){ console.log('성공', message); }
function printScore( score, onSuccess, onError ){
    if( score >= 80 ){ onSuccess("합격")}
    else( onError("불합격") )
}
// 콜백함수 방식으로 함수 호출 , () 주의할점: 인수에 함수 전달시 함수실행x 함수정의o
printScore( 50, printSucces , ( message ) => { console.log("실패" +message)} ) // 콜백함수 방식으로 함수 호출, 주의할점 : 인수에 함수 전달시 함수실행x 함수정의
// 함수명 ( 3+3 ); 인수:6  // 함수명( plus(3,3) ) , 인수: 6  // 함수명( plus ), 인수 : plus함수

// 10. 동기식 : 먼저 호출한 함수/기능 결과가 올때까지 대기상태 , 동기화 await
//      비동기 : 먼저 호출한 함수/기능 순서 상관없이 결과반환, axios 
// axios 비동기통신이다. 동기화로 만드는 방법
// (1) 선언 함수앞에 async
const backLoad = async ( ) => {
    // (2) axios 앞에 await
    const resopnse = await axios();
 }
 backLoad();