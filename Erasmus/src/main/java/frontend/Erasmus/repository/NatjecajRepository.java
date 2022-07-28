package frontend.Erasmus.repository;

import frontend.Erasmus.model.Mobilnost;
import frontend.Erasmus.model.Natjecaj;
import frontend.Erasmus.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NatjecajRepository extends JpaRepository<Natjecaj, Long> {
    List<Natjecaj> findAllByMobilnost(Mobilnost mobilnost);

    List<Natjecaj> findByUser(User user);
}
