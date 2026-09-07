package example.Spring_Practice5.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import example.Spring_Practice5.model.dto.BoardDto;
import example.Spring_Practice5.model.dto.CommentDto;
import example.Spring_Practice5.model.entity.BoardEntity;
import example.Spring_Practice5.model.repository.BoardRepository;

@Service 
public class BoardService {
    @Autowired 
    private BoardRepository boardRepository;

    // 게시글 저장
    public boolean save(BoardDto boardDto){
        BoardEntity boardEntity = boardDto.toEntity();
        BoardEntity saved = boardRepository.save(boardEntity);
        if(saved.getBoardId() >= 1 ){return  true;}
        return  false;
    }

    // 게시글 전체 출력
    public List<BoardDto> findAll(){
        List<BoardEntity> BoardEntitise = boardRepository.findAll();
        List<BoardDto> boardDtos = new ArrayList<>();
        BoardEntitise.forEach((boardEntity)->{
            BoardDto boardDto = BoardDto.from(boardEntity);

            boardEntity.getCommentEntities().forEach( (comment) -> {
                CommentDto commentDto = CommentDto.from(comment);
                boardDto.getCommentDtos().add(commentDto);
            });
            boardDtos.add(boardDto);
        });
        return  boardDtos;
    }

    // 게시글 삭제
    public boolean delete(Integer boardId, String password){
        Optional<BoardEntity> optional = boardRepository.findById(boardId);
        if(optional.isPresent()){
            BoardEntity entity = optional.get();
            if(entity.getPassword().equals(password)){
                boardRepository.deleteById(boardId);
                return  true;
            }
        }
        return  false;
    }
    
}
