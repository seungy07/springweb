package example.Spring_Practice3;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class P3_Service {
    private final P3_Repository p3_Repository;

    // 영화 전체 조회: 모든 영화 목록을 조회
    public List<P3_Dto> AllPrint(){
        List<P3_Entity> entities = p3_Repository.findAll();
        List<P3_Dto> list = new ArrayList<>();
        entities.forEach( (obj)-> {
            list.add(P3_Dto.from(obj));
        });
        return list;
    }

    // 영화 개별 조회: 영화번호(movieid)를 기준으로 특정 영화 상세 정보 조회
    public P3_Dto findOne(int movieId){
       Optional<P3_Entity> optional = p3_Repository.findById( movieId );
       if( optional.isPresent() ){
            // entity -> dto
            P3_Entity entity = optional.get();
            P3_Dto dto = P3_Dto.from(entity);
            return dto;
       }
       return null;
    }

    // 영화 등록: 새로운 영화 정보를 입력받아 DB에 저장
    public boolean save(P3_Dto p3_Dto){
        // dto - > entity
        P3_Entity saved = p3_Repository.save(p3_Dto.toEntity());
        if(saved.getMovieid() >= 1){
            return true;
        }
        return false;
    }

    // 특정 영화 삭제: 영화번호(movieid)를 기준으로 해당 영화 삭제
    public boolean delete(int movieId){
        Optional<P3_Entity> optional = p3_Repository.findById(movieId);
        if(optional.isPresent()){
            p3_Repository.deleteById(movieId);
            return true;
        }
        return false;
    }

    // 특정 영화 수정: 영화번호(movieid)를 기준으로 영화번호 외 모든 영화 정보 수정
    @Transactional
    public boolean update(P3_Dto p3_Dto){
        Optional<P3_Entity> optional = p3_Repository.findById( p3_Dto.getMovieid() );
        if(optional.isPresent()){
            P3_Entity entity = optional.get();
            entity.setDirector(p3_Dto.getDirector());
            entity.setRating(p3_Dto.getRating());
            entity.setReleasedate(p3_Dto.getReleasedate());
            entity.setTitle(p3_Dto.getTitle());
            return true;
        }
        return false;
    }

}
// [조건 5] 서비스 구현 : Service 클래스를 작성하여 아래 기능을 구현한다.
// 영화 전체 조회: 모든 영화 목록을 조회
// 영화 등록: 새로운 영화 정보를 입력받아 DB에 저장
// 영화 개별 조회: 영화번호(movieid)를 기준으로 특정 영화 상세 정보 조회
// 특정 영화 삭제: 영화번호(movieid)를 기준으로 해당 영화 삭제
// 특정 영화 수정: 영화번호(movieid)를 기준으로 영화번호 외 모든 영화 정보 수정