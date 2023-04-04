package com.bookmanagerdb.bookmanagerdb.config.auth;

import com.bookmanagerdb.bookmanagerdb.dao.UserRepository;
import com.bookmanagerdb.bookmanagerdb.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        User user = userRepository.findById(Long.valueOf(userId)).orElse(null);
        if (user != null){
            return new UserDetailsImpl(user);
        }
        throw new UsernameNotFoundException("User id not found");
    }
}
