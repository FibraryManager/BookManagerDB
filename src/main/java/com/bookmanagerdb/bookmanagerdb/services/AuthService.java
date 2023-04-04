package com.bookmanagerdb.bookmanagerdb.services;

import com.bookmanagerdb.bookmanagerdb.controller.constants.RoleConstants;
import com.bookmanagerdb.bookmanagerdb.dao.UserRepository;
import com.bookmanagerdb.bookmanagerdb.entity.User;
import com.bookmanagerdb.bookmanagerdb.entity.dto.UserDTO;
import com.bookmanagerdb.bookmanagerdb.utils.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;

    // 注册用户
    public User saveUser(UserDTO userDTO, String role){
        String type = "";
        if (RoleConstants.ROLE_USER.equals(role)) type = RoleConstants.ROLE_USER;
        else type = RoleConstants.BOOK_ADMIN;
        User user = new User(
                new BCryptPasswordEncoder().encode(userDTO.getPassword()),
                userDTO.getNickname(),
                userDTO.getPhoneNumber(),
                type
        );
        return userRepository.save(user);
    }

    public String generateTokenForUser(User user) {
        Map<String, String> map = new HashMap<>();
        map.put("userId", user.getUserId().toString());
        String token = TokenUtil.GenerateToken(map);
        return token;
    }
}
