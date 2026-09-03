package example.day41_day05_0903;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class TestService {
    @Autowired TestRepository testRepository;

    // 1. 전체조회
    public List<TestDto> 전체조회(){
        // 1-1. 모든 엔티티 조회
        List<TestEntity> entities  =  testRepository.findAll();
        // 1-2. 모든 엔티티 -> DTO 변환하기
        // 빈 리스트 생성
        List<TestDto> list = new ArrayList<>();
        // 모든 엔티티 반복하여 모든 DTO 변환해서 새로운 리스트 저장
        // 리스트객체.forEach( (반복변수)->{ } )
        entities.forEach( (Entity)->{
            // - 리스트내 하나씩 entity(반복변수)에 대입 반복
            // - TestDto내 entity -> dto 변환함수 : from
            list.add(TestDto.from(Entity));
        }  );
        return list;
    }

    // 2. 등록
    public boolean 등록(TestDto testDto){
        // 1. dto -> entity : toEntity()
        TestEntity saved = testRepository.save( testDto.toEntity() );
        if(saved.getNo() >= 1) return true;
        return false;
    }

    // 3. 수정
    @Transactional
    public boolean 수정(TestDto testDto){
        Optional<TestEntity> optional = testRepository.findById( testDto.getNo() );
        if( optional.isPresent() ){
            TestEntity entity = optional.get();
            entity.setDescri(testDto.getDescri());
            entity.setName(testDto.getName());
            entity.setPrice(testDto.getPrice());
            return true;
        }
        return false;

    }
    // name, descri , price , create_Date, update_Time
}
