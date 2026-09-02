package example.Spring_Pratice2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface P_Repository extends JpaRepository<P_Entity, Integer> { }
