package example.Spring_P;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository 
public interface CategoriesRepository extends JpaRepository<CategoriesEntity, Integer> {
    
}
