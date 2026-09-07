package example.Spring_P_Smart_Clotset;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository 
public interface  UserRepostiory extends JpaRepository<UserEntity, Integer> {
    
}
