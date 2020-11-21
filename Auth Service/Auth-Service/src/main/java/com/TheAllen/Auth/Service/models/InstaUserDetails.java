package com.TheAllen.Auth.Service.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

public class InstaUserDetails extends User implements UserDetails {

    public InstaUserDetails() {}

    public InstaUserDetails(final User user) {
        super(user);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return super.getRoles()
                    .stream()
                    .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName()))
                    .collect(Collectors.toSet());
    }

    @Override
    public boolean isAccountNonExpired() {
        return super.isActive();
    }

    @Override
    public boolean isAccountNonLocked() {
        return super.isActive();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return super.isActive();
    }

    @Override
    public boolean isEnabled() {
        return super.isActive();
    }
}
