package frontend.Erasmus.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import java.time.Instant;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Prijava {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long prijavaId;
    @NotBlank(message = "Post Name cannot be empty or Null")
    private String prijavaName;
    @Nullable
    private String url;
    @Nullable
    @Lob
    private String opis;
    private Integer brojPrijava = 0;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    private User user;
    private Instant createdDate;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "id", referencedColumnName = "id")
    private Natjecaj natjecaj;
    private boolean prihvacen;
    private boolean odbijen;
}
