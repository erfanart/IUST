package com.example.demo.security.config;

import com.example.demo.entity.Admin;
import com.example.demo.entity.SuperAdmin;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
@Getter
public class SuperAdminUserDetail implements UserDetails {
    private final SuperAdmin superAdmin;

    public SuperAdminUserDetail(SuperAdmin superAdmin) {
        this.superAdmin = superAdmin;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("SUPER"));
    }

    @Override
    public String getPassword() {
        return superAdmin.getPassword();
    }

    @Override
    public String getUsername() {
        return superAdmin.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
