package frontend.Erasmus.model;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ADMIN,USER,PROFESOR;

    @Override
    public String getAuthority() {
        return null;
    }
}
