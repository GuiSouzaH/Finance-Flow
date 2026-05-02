package financeflow.security;

import financeflow.model.UsuarioEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.List;
import java.util.UUID;


public class UsuarioDetails implements UserDetails {

    private final UsuarioEntity usuarioEntity;

    //metodo pra pegar so o ID pro controller
    public UUID getId() {
        return usuarioEntity.getId();
    }

    public UsuarioDetails(UsuarioEntity usuarioEntity) {
        this.usuarioEntity = usuarioEntity;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + usuarioEntity.getRole().name()));
    }

    @Override
    public String getPassword() {
        return usuarioEntity.getSenha();
    }

    @Override
    public String getUsername() {
        return usuarioEntity.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
