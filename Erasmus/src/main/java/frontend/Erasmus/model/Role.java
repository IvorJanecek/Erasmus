package frontend.Erasmus.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="role")
@Getter
@Setter

public class Role {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(name="role_id")
        private int roleId;

        @Column(name="role")
        private String role;

        public Role() {

        }

        public Role(String role) {
            super();
            this.role = role;
        }

    }
