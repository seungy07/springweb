package example.Spring_day44.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import example.Spring_day44.model.entity.BoardEntity;

@Repository 
public interface BoardRepository extends JpaRepository<BoardEntity, Integer> {
    
}
