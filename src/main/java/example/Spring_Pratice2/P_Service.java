package example.Spring_Pratice2;

import example.Spring_Pratice2.P_Repository;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class P_Service {
    private final P_Repository p_Repository;

    // 게시물 전체 조회
    public List<P_Entity> findAll(){
        return p_Repository.findAll();
    }

    // 게시물 등록
    public boolean save(P_Entity p_Entity){
        P_Entity saved = p_Repository.save(p_Entity);
        if(saved.getNo() >= 1){ return true ;}
        return false;
    }

    // 게시물 삭제
    public boolean delete(int no){
        p_Repository.deleteById(no);
        return true;  
    }

    // 게시물 수정
    @Transactional
    public boolean update(P_Entity p_Entity){
        Optional<P_Entity> optional = p_Repository.findById( p_Entity.getNo() );
        if( optional.isPresent() ){
            P_Entity entity = optional.get();
            entity.setContent( p_Entity.getContent() );
            entity.setWriter( p_Entity.getWriter() );
            return true;
        }
        return false;
    }
    
}
