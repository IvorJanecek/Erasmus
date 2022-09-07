package frontend.Erasmus.repository;

import frontend.Erasmus.model.Natjecaj;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NatjecajRepository extends JpaRepository<Natjecaj, Long> {
    Optional<Natjecaj> findByName(String natjecajName);

}

