package frontend.Erasmus.model;

import lombok.*;
import org.jetbrains.annotations.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.Instant;
import java.util.List;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Mobilnost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mobilnostId;
    @NotBlank(message = "Post name cannot be empty")
    private String mobilnostName;
    @Nullable
    private String url;
    @Nullable
    @Lob
    private String description;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId" , referencedColumnName = "userId")
    private User user;
    private Instant createdDate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "id")
    private Natjecaj natjecaj;
    @Enumerated(EnumType.STRING)
    private Status status;
}
