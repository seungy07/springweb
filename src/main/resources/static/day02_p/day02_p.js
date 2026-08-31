// 조회
async function boardfindAll() {
    console.log("대기명단 전체 조회")
    
    let tbody = document.querySelector('.boardList');
    let html = "" ;
    const response = await axios.get("http://127.0.0.1:8080/list/findAll");
    const data = response.data;  console.log(data);
    for(let i=0; i<=data.length-1; i++){
        const obj = data[i];
        html += `<tr>
                    <td> ${obj.number} </td> <td> ${obj.n} </td>
                    <td> <button onClick="listUpdate('${obj.number}')">수정</button> <button onClick="listDelete('${obj.number}')">삭제 </button> </td > <!-- 테이터셀(한칸) -->
                </tr>`
    }
    tbody.innerHTML = html;
}
boardfindAll();

// 등록
async function ListSave() {
    const number = document.querySelector('.number').value;
    const n = document.querySelector(".n").value;
    const response = await axios.post(`/list/save?number=${number}&n=${n}`);
    console.log(response); console.log(response.data);
    if(response.data){alert('등록성공'); boardfindAll();}
    else{alert('등록실패');}
}

// 수정
async function listUpdate(number) {
    let n = Number(prompt("수정 인원수: "));
    const response = await axios.put(`/list/update?number=${number}&n=${n}`);
    console.log(response.data); console.log(response);
    if(response.data){alert('수정 성공'); boardfindAll();}
    else{alert('수정실패')}
}

// 삭제
async function listDelete(number) {
    const response = await axios.delete(`/list/delete?number=${number}`);
    console.log(response);
    console.log(response.data);
    if(response.data){alert('삭제성공'); boardfindAll();}
    else{alert('삭제실패');}
    
}