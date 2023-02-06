package com.family.gati.security;

import com.family.gati.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

// Authentication 객체를 커스텀한 class
public class CustomUserDetails implements UserDetails {
    private int userSeq;
    private String email;
    private Collection<? extends GrantedAuthority> authorities;
    private Map<String, Object> attributes;

    public CustomUserDetails(int userSeq, String email, Collection<? extends GrantedAuthority> authorities) {
        this.userSeq = userSeq;
        this.email = email;
        this.authorities = authorities;
    }

    public static CustomUserDetails create(User user) {
        List<GrantedAuthority> authorities = Collections.
                singletonList(new SimpleGrantedAuthority("ROLE_USER"));

        return new CustomUserDetails(
                user.getUserSeq(),
                user.getEmail(),
                authorities
        );
    }

//    public static CustomUserDetails create(User user, Map<String, Object> attributes) {
//        CustomUserDetails userDetails = CustomUserDetails.create(user);
//        userDetails.setAttributes(attributes);
//        return userDetails;
//    }

    // UserDetail Override
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getUsername() {
        return String.valueOf(userSeq);
    }

    @Override
    public String getPassword() {
        return null;
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
