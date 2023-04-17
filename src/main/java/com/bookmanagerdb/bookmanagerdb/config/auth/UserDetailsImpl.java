package com.bookmanagerdb.bookmanagerdb.config.auth;

import com.bookmanagerdb.bookmanagerdb.controller.constants.RoleConstants;
import com.bookmanagerdb.bookmanagerdb.entity.User;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class UserDetailsImpl implements UserDetails {
    @Getter
    private final User user;
    public UserDetailsImpl(User user){
        this.user = user;
    }

    // 获取用户身份
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> roleList = new LinkedList<>();
        if (RoleConstants.BOOK_ADMIN.equals(user.getType())){
            roleList.add(new SimpleGrantedAuthority(RoleConstants.BOOK_ADMIN));
        }
        if (RoleConstants.ROLE_ADMIN.equals(user.getType())){
            roleList.add(new SimpleGrantedAuthority(RoleConstants.ROLE_ADMIN));
        }
        roleList.add(new SimpleGrantedAuthority(RoleConstants.ROLE_USER));
        return roleList;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUserId().toString();
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
