package frontend.Erasmus.repository;

import frontend.Erasmus.model.Natjecaj;
import frontend.Erasmus.model.Prijava;
import frontend.Erasmus.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PrijavaRepository extends JpaRepository<Prijava, Long> {
    List<Prijava> findAllByNatjecaj(Natjecaj natjecaj);

    List<Prijava> findByUser(User user);
}
