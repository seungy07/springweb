package example.day60_day14_1002;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletResponse;


@CrossOrigin(origins = "http://localhost:5173") // React 로컬 포트 허용
@RestController
@RequiredArgsConstructor 
@RequestMapping("/api/board")
public class BoardController {

    private final FileService fileService;
    private final BoardService boardService;

    // 등록
    @PostMapping("/write")
    public boolean write(@ModelAttribute BoardDto dto) {  // DTO 매핑시 @RequestBody, 사용 X,, application/json 아님
        // @ModelAttribute 생략도 가능(기본값)
        return boardService.boardWrite(dto);
    }

    // 전체 조회
    @GetMapping("/list")
    public List<BoardDto> list() {
        return boardService.boardFindAll();
    }

    // 개별 조회
    @GetMapping("/view")
    public BoardDto view(@RequestParam ( name = "id") Long id) {
        return boardService.boardFindById(id);
    }

    // [4] 다운로드
    @GetMapping("/download/{id}")
    public void download(@PathVariable(name = "id") Long id, HttpServletResponse response ) {
        // 1. 다운로드 받을 게시물 번호와 HTTP 응답 객체 가져온다
        // 2. 다운로드 받을 게시물 번호의 업로드 파일명 조회
        String fileName = boardService.getFileName(id);
        // 3. 파일명이 존재하면 다운로드 진행
        if(fileName != null){
            fileService.fileDownload(fileName, response);
        }

    }
    

}