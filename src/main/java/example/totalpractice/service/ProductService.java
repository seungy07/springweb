package example.totalpractice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import example.totalpractice.model.dto.ProductDto;
import example.totalpractice.model.dto.ReviewDto;
import example.totalpractice.model.entity.CategoryEntity;
import example.totalpractice.model.entity.ProductEntity;
import example.totalpractice.model.repository.CategoryRepository;
import example.totalpractice.model.repository.ProductRepoistory;

@Service 
public class ProductService {
    @Autowired 
    private ProductRepoistory productRepoistory;
    @Autowired 
    private CategoryRepository categoryRepository;

    // 수정
    @Transactional 
    public boolean update(ProductDto productDto){
        
        Optional<ProductEntity> optional = productRepoistory.findById( productDto.getBno() );
        if(optional.isPresent()){
            ProductEntity pEntity = optional.get();
            pEntity.setName(productDto.getName());
            pEntity.setPrice(productDto.getPrice());
            
            return true;
        }
        return false;
    }

    // 삭제
    public boolean delete(Integer bno){
        ProductEntity entity = productRepoistory.findById(bno).orElse(null);
        if(entity != null){
            productRepoistory.deleteById(bno);
            return  true;
        }
        return  false;
    }    

    // 등록
    public boolean 제품등록(ProductDto productDto){
        CategoryEntity categoryEntity = categoryRepository.findById(productDto.getCno()).orElse(null);
        if(categoryEntity != null){
            ProductEntity productEntity = productDto.toEntity(categoryEntity);

            ProductEntity savedEntity = productRepoistory.save(productEntity);
            if (savedEntity.getBno()>=1) {
                return true;
            }
        }
        return  false;
    }

    // 조회
    public List<ProductDto> 제품전체조회(){
        List<ProductEntity> productEntities = productRepoistory.findAll();
        List<ProductDto> productDtos = new ArrayList<>();

        productEntities.forEach((productEntity)->{
            ProductDto productDto = ProductDto.from(productEntity);
            productEntity.getReviewEntities().forEach( (ReviewEntity) -> {
                ReviewDto reviewDto = ReviewDto.from(ReviewEntity);
                productDto.getReviewDtos().add(reviewDto);
            });
            productDtos.add(productDto);
        });
        return productDtos;
    }
}
