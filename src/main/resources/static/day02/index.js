
console.log("오픈")

// [1] 전체조회, function 함수명( 매개변수명 ){ }
async function boardFindAll(){
    console.log( `boardFindALl 열림 `);
    // 1. 어디에: html table 본문에, 식별, .클래스명 vs #ID명
    // document(HTML문서).query(질의/요청)Selector("선택자")
    let tbody = document.querySelector('.boardList'); 

    // 2. 무엇을 (HTTP 통신 (AXION) 이용한 백엔드에게 요청)
    // *await axios.HTTP메소드명( "HTTP주소" )  * 현재 함수명 앞에 async 작성
    // * 동기화 하는 이유; 해당 통신 이후에 아래 코드 실행하기 위해
    // * 비동기화( 요청 후 응답 대기없음 ), 동기화( 요청 후 응답 대기 )
    let html = "";  console.log(tbody); 
    const 응답결과 = await axios.get("http://127.0.0.1:8080/board/findAll");  // 응답을 받고 나서 (대기) 출력 (대기 상태가 있어야 함)
    console.log(응답결과);

    const 응답자료 = 응답결과.data;  // list형태로 출력 (ArrayList 로 출력받는 것으로 정함)
    console.log(응답자료);
    for(let i=0; i <=응답자료.length -1; i++){
        const 게시물객체 = 응답자료[i];
        // ` 백틱이란? 문자열과 문자열 사이에 ${} 이용하여 변수 대입가능
        html += `<tr>
                    <td> ${게시물객체.no} </td> <td> ${게시물객체.writer} </td> <td> ${게시물객체.content} </td>
                    <td> <button>수정</button> <button>삭제 </button> </td > 
                </tr>`
    }

    // 3. 출력
    tbody.innerHTML = html;  
}
boardFindAll(); // * HTML(JS포함) 열릴 때 최초 1번 실행