package frontend.Erasmus.repository;

import frontend.Erasmus.model.Mobilnost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MobilnostRepository extends JpaRepository<Mobilnost, Long> {
    Optional<Mobilnost> findByName(String mobilnostName);
}
