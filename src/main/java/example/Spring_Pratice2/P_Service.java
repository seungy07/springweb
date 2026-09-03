package example.Spring_Pratice2;

import example.Spring_Pratice2.P_Repository;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service // @Component 포함
@RequiredArgsConstructor
public class P_Service {
    private final P_Repository p_Repository;

    // 게시물 전체 조회
    public List<P_Entity> testPrint(){
        return p_Repository.findAll();
    }
    
    // 게시물 개별 조회
    public P_Entity testDetail(int no){
        // *** Optional 클래스란? 객체 사용시 null 예외가 발생하는 경우 안전하게 메소드 제공
        // Optional<P_Entity> optional = p_Repository.findById(no);
        // if( optional.isPresent() ){  // 존재하면 꺼내기 .isEmpty 도 가능
        //      P_Entity entity = optional.get()
        //      return entity
        // } return null;
        List<P_Entity> optional = p_Repository.findAll(); 
        for(P_Entity entity : optional){
            if (entity.getNo() == no) {
                return entity;
            }
        }
        return null;
    }

    // 게시물 등록
    public boolean testWrite(P_Entity p_Entity){
        P_Entity saved = p_Repository.save(p_Entity);
        if(saved.getNo() >= 1){ return true ;}
        return false;
    }

    // 게시물 삭제
    public boolean testDelete(int no){
        // 1. findById 이용한 삭제 엔티티 확인
        Optional<P_Entity> optional =p_Repository.findById(no);
        if(optional.isPresent()){
            p_Repository.deleteById(no);  // .delete( opional.get() )
            return true;
        }
        return false;
    }

    // 게시물 수정
    @Transactional
    public boolean testUpdate(P_Entity p_Entity){
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
