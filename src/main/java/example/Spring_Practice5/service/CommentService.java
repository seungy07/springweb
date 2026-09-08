package example.Spring_Practice5.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import example.Spring_Practice5.model.dto.CommentDto;
import example.Spring_Practice5.model.entity.BoardEntity;
import example.Spring_Practice5.model.entity.CommentEntity;
import example.Spring_Practice5.model.repository.BoardRepository;
import example.Spring_Practice5.model.repository.CommentRepository;
import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class CommentService {
    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;

    // 댓글 저장
    public boolean save(CommentDto commentDto){
        // DTO 에서 게시글 번호 꺼내기
        Integer boardId = commentDto.getBoardId();
        // 해당 게시글 조회
        BoardEntity boardEntity = boardRepository.findById(boardId).orElse(null);

        CommentEntity commentEntity = commentDto.toEntity();
        commentEntity.setBoardentity(boardEntity); // 댓글에 해당 게시물들 세팅
        CommentEntity saved = commentRepository.save(commentEntity);
        if(saved.getCommentId() >= 1){return  true;}
        return  false;
    }

    // 댓글 삭제
    public boolean delete( Integer commentId, String password){
        Optional<CommentEntity> optional = commentRepository.findById(commentId);
        if(optional.isPresent()){
            CommentEntity commentEntity = optional.get();
            if(commentEntity.getPassword().equals(password)){
                commentRepository.deleteById(commentId);
                return true;
            }
        }
        return false; // 댓글 없거나 비밀번호 틀린경우
    }


    
}
