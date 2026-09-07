package example.Spring_P_Smart_Clotset;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddService {
    @Autowired private ClosetRepository closetRepository;
    @Autowired private  UserRepostiory userRepostiory;
    @Autowired private  CategoriesRepository categoriesRepository; 


    // 의류 등록
    public boolean clothesAdd(ClothesDto clothesDto, int mno){
        // 회원 번호 조회
        // Optional로 반환하면서 값 또는 null 반환
        UserEntity userEntity = userRepostiory.findById(mno).orElse(null);
        if(userEntity == null){ return  false;}

        // 카테고리 조회
        CategoriesEntity categoriesEntity = categoriesRepository.findById( clothesDto.getCno()).orElse(null);
        if(categoriesEntity == null){ return  false;}

        // Dto -> entity
        ClothesEntity clothesEntity = clothesDto.toEntity();

        clothesEntity.setUserEntity((userEntity));  // 변환 후 회원번호 세팅 fk
        clothesEntity.setCategoriesEntity(categoriesEntity); // 변환후 카테고리 번호 세팅 fk

        // 저장
        ClothesEntity saved = closetRepository.save(clothesEntity);

        if(saved.getClno() >= 1){return true;}
        else{ return  false; }


    }
}
