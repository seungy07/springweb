package example.day55_day09_react_day07.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import example.day55_day09_react_day07.model.entity.ApiEntity;

@Repository 
public interface  ApiRepository extends JpaRepository<ApiEntity, Integer> {
    
}
