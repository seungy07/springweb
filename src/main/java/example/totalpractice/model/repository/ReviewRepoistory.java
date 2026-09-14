package example.totalpractice.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import example.totalpractice.model.entity.ReviewEntity;

@Repository 
public interface  ReviewRepoistory extends JpaRepository<ReviewEntity, Integer> {
    
}
