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