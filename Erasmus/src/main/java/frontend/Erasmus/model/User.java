package frontend.Erasmus.model;

import lombok.*;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.time.Instant;
import java.util.*;

@Data
@AllArgsConstructor
@Getter
@Setter
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

    private Date dob;



    @Column(unique=true)
    @Email
    @NotEmpty(message = "Email is required")
    @Pattern(regexp = "[A-Za-z].+[A-Za-z]+[@]+[student].+[mev].+[hr]|[A-Za-z].+[A-Za-z]+[@]+[mev].+[hr]|[A-Za-z]+[@]+[mev].+[hr]")
    private String email;


    private Instant created;

    private boolean enabled;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id",referencedColumnName = "userId"),inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "role_id"))
    private Collection<Role> roles;

    public User() {
    }

    public User(User user) {
        this.dob = user.getDob();
        this.email = user.getEmail();
        this.roles = user.getRoles();
        this.username = user.getUsername();
        this.prezime =user.getPrezime();
        this.userId = user.getUserId();
        this.password = user.getPassword();
    }


}
