package frontend.Erasmus.repository;

import frontend.Erasmus.model.Mobilnost;
import frontend.Erasmus.model.Natjecaj;
import frontend.Erasmus.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MobilnostRepository extends JpaRepository<Mobilnost, Long> {
    List<Mobilnost> findAllByNatjecaj(Natjecaj natjecaj);

    List<Mobilnost> findByUser(User user);
}