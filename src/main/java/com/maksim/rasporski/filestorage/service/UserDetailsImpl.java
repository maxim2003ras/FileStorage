package com.maksim.rasporski.filestorage.service;

import com.maksim.rasporski.filestorage.entity.AppUser;
import com.maksim.rasporski.filestorage.entity.UserRoles;
import com.maksim.rasporski.filestorage.logic.Roles;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Maksim Rasporski
 * @link https://vk.com/maximrasporsky
 */
public class UserDetailsImpl implements UserDetails {
    private final String username;
    private final String password;
    private Set<GrantedAuthority> userRoles = new HashSet<>();

    public UserDetailsImpl(final AppUser user) {
        username = user.getEmail().toLowerCase();
        password = user.getPassword();
        if (user.getRoles() != null) {
            userRoles.add(new SimpleGrantedAuthority(user.getRoles().getRole().toUpperCase()));
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return userRoles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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
