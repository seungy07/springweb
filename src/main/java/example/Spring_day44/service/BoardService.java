package example.Spring_day44.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import example.Spring_day44.model.dto.BoardDto;
import example.Spring_day44.model.dto.CommentDto;
import example.Spring_day44.model.entity.BoardEntity;
import example.Spring_day44.model.entity.CommentEntity;
import example.Spring_day44.model.repository.BoardRepository;
import example.Spring_day44.model.repository.CommentRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Service 
@NoArgsConstructor @AllArgsConstructor @Builder @Data 
public class BoardService {
    @Autowired 
    private BoardRepository boardRepository;
    @Autowired private CommentRepository CommentRepository;

    // 게시글 등록
    public boolean save(BoardDto boardDto){
        BoardEntity boardEntity = boardDto.toEntity();
        BoardEntity saved = boardRepository.save(boardEntity);
        if(saved.getId() >= 1){return  true;}
        return false;
    }

    // 게시글 전체 조회
    public List<BoardDto> findAll(){
        List<BoardEntity> boardEntities = boardRepository.findAll();

        // DTO 담을 
        List<BoardDto> boardDtos = new ArrayList<>();

        boardEntities.forEach((board) -> {
            BoardDto boardDto = BoardDto.from(board); // 게시글 하나씩 DTo로 변환
            List<CommentDto> cDtos = new ArrayList<>();
            
            // 게시글 안에 있는 댓글들도 entity -> dto
            board.getCommentEntities().forEach((comment) -> {
                CommentDto commentDto = CommentDto.from(comment); // 댓글 하나씩 dto 변환
                // 변환한 dto를 다시 해당 게시물에 추가
                cDtos.add(commentDto);
            });
            boardDto.setComments(cDtos);
            boardDtos.add(boardDto);
        });
        return boardDtos;
    }

    // 게시글 삭제
    public boolean delete(Integer id, String password){
        Optional<BoardEntity> optional = boardRepository.findById(id);
        if(optional.isPresent()){
            BoardEntity boardEntity = optional.get();
            if(boardEntity.getPassword().equals(password)){
                boardRepository.deleteById(id);
                return  true;
            }
        }
        return  false;
    }
    
}
