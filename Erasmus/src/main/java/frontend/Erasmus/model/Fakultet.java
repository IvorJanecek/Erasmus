package frontend.Erasmus.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "fakultet")
@AllArgsConstructor
@NoArgsConstructor
public class Fakultet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private String nazivFakulteta;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
