package example.totalpractice.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import example.totalpractice.model.entity.ProductEntity;

@Repository 
public interface  ProductRepoistory extends JpaRepository<ProductEntity, Integer> {
    
}
