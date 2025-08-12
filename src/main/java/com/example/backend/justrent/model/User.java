package com.example.backend.justrent.model;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.backend.justrent.services.Common;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User implements UserDetails {
    @Id
    @Column(name = "userid", unique = true, nullable = false)
    private String id;
    private String shortCode="USR-";
    private String username;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(name = "verification_code")
    private String verificationCode;
    @Column(name = "verification_expiration")
    private LocalDateTime verificationCodeExpiresAt;
    private String firstName;
    private String lastName;
    private String gender;
    private boolean enabled;

    // constructor for creating an unverified user
    public User( String email, String password,String firstName,String lastname,String gender) {
        Common common = new Common();
        this.id =common.generateUserId(shortCode);
        this.firstName=firstName;
        this.lastName= lastname;
        this.username = firstName+String.valueOf((int)(Math.random() * 100000));
        this.email = email;
        this.password = password;
        this.gender= gender;
    }

    // default constructor
    public User() {
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    // TODO: add proper boolean checks
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
        return enabled;
    }
}
