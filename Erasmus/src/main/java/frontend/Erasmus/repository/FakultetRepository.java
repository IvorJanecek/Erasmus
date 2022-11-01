package frontend.Erasmus.repository;

import frontend.Erasmus.model.Fakultet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FakultetRepository extends JpaRepository<Fakultet, Long> {


}
