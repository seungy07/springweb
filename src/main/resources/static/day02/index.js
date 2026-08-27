
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
                    <td> <button onClick="boardUpdate(${게시물객체.no})">수정</button> <button onClick="boardDelete(${게시물객체.no})">삭제 </button> </td > 
                </tr>`
    }

    // 3. 출력
    tbody.innerHTML = html;  
}
boardFindAll(); // * HTML(JS포함) 열릴 때 최초 1번 실행

// [2] 등록
async function boardSave() {  // 동기화 함수 async
    // 1. 입력받은 값 가져오기
    const content = document.querySelector('.content').value;
    const writer = document.querySelector('.writer').value;
    // 2. 저장 : axios 이용하여 백엔드에게 저장 요청하고 응답받기
    // await axios.http메소드("주소") , 도메인 생력가능
    const response = await axios.post(`/board/save?content=${content}&writer=${writer}`);
    console.log(response); // 반환 타입을 boolean 설정해 놓ㅇㅁ

    // 3. 결과출력
    if(response){alert('저장성공'); boardFindAll(); } // 성공하면 새로고침
    else{alert('저장 실패');}
}

// [3] 수정 <button onClick="boardUpdate(${게시물객체.no})">수정 
async function  boardUpdate(no) {
    // 1. 수정할 내용 입력받기 prompt
    const content = prompt("수정 내용: ");
    // 2. 수정처리 : axios 이용하여 백엔드에게 수정 요청/응답
    const response = await axios.put(`/board/update?no=${no}&content=${content}`);
    // 3. 결과
    if(response){alert('수정 성공'); boardFindAll();}
    else{alert('수정 실패');}
}

// [4] 삭제 <button onClick="boardDelete(${게시물객체.no})">삭제
async function  boardDelete(no) {
    const response = await axios.delete(`/board/delete?no=${no}`);  // "" 쓸거면 마지막에 +매개변수 
    console.log(response.data) // true 나옴 , 차이가 있나 
    if(response){alert('삭제 성공'); boardFindAll();}
    else{alert('삭제 실패');}
}