package frontend.Erasmus.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.time.Instant;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @NotBlank(message = "Username is required")
    private String username;

    @NotBlank(message = "Password is required")
    private String password;

    private String prezime;

    private String kontakt;

    private String Grad;

    private Date Dob;



    @Email
    @NotEmpty(message = "Email is required")
    @Pattern(regexp = "[A-Za-z].+[A-Za-z]+[@]+[student].+[mev].+[hr]|[A-Za-z].+[A-Za-z]+[@]+[mev].+[hr]|[A-Za-z]+[@]+[mev].+[hr]")
    private String email;


    @Enumerated(EnumType.STRING)
    private Role role;

    private Instant created;

    private boolean enabled;

}
