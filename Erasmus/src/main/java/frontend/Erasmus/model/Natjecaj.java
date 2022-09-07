package frontend.Erasmus.model;

import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.Instant;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Getter
@Setter
public class Natjecaj {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Community name is required")
    private String name;
    @NotBlank(message = "Description is required")
    private String description;
    @OneToMany(fetch = FetchType.LAZY)
    private List<Mobilnost> mobilnosts;
    private Instant createDate;
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
    private Date Od;
    private Date Do;
    private String Ustanova;
}
