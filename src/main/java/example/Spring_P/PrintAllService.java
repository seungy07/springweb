package example.Spring_P;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrintAllService {
    @Autowired 
    private ClothesRepository clothesRepository;

    // 의류 전체조회
    public ArrayList<ClothesDto>clothesPrintAll(){
        
        // DB의 모든 의류 조회
        List<ClothesEntity> entityList = clothesRepository.findAll();

        // 반환할 DTO 리스트 생성
        ArrayList<ClothesDto>dtoList = new ArrayList<>();

        // Entity -> DTO 변환 
        for(ClothesEntity entity : entityList ){

            ClothesDto dto = ClothesDto.builder()
            .clno(entity.getClno())
            .mno(entity.getUserEntity().getMno())
            .cno(entity.getCategoriesEntity().getCno())
            .clname(entity.getClname())
            .clcolor(entity.getClcolor())
            .retype(entity.getRetype())
            .build();
        
            dtoList.add(dto);
        }

        // DTO 리스트 반환
        return dtoList;
    }
}
