package example.totalpractice.model.repository;

import org.hibernate.boot.models.JpaAnnotations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import example.totalpractice.model.entity.CategoryEntity;

@Repository 
public interface CategoryRepository extends JpaRepository<CategoryEntity , Integer > {
    
}
