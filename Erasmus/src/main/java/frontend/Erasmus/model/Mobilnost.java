package frontend.Erasmus.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.Instant;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Getter
@Setter
public class Mobilnost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Community name is required")
    private String name;
    @NotBlank(message = "Description is required")
    private String description;
    @OneToMany(fetch = FetchType.LAZY)
    private List<Natjecaj> natjecajs;
    private Instant createDate;
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
}
