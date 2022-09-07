package frontend.Erasmus.repository;

import frontend.Erasmus.model.Ugovori;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UgovoriRepository extends JpaRepository<Ugovori, String> {
}
