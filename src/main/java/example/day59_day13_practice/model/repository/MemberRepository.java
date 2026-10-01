package example.day59_day13_practice.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import example.day59_day13_practice.model.entity.MemberEntity;

@Repository 
public interface  MemberRepository extends JpaRepository<MemberEntity, Long> {
    
}
