package com.bookmanagerdb.bookmanagerdb.controller.common;

import com.bookmanagerdb.bookmanagerdb.config.CurrentAuth;
import com.bookmanagerdb.bookmanagerdb.controller.constants.RoleConstants;
import com.bookmanagerdb.bookmanagerdb.dao.UserRepository;
import com.bookmanagerdb.bookmanagerdb.entity.User;
import com.bookmanagerdb.bookmanagerdb.entity.dto.UserDTO;
import com.bookmanagerdb.bookmanagerdb.entity.dto.UserUpdateDTO;
import com.bookmanagerdb.bookmanagerdb.services.AuthService;
import lombok.Data;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthService authService;

    @Autowired
    private HttpServletResponse httpServletResponse;


    @PostMapping("/signin")
    public String signin(@RequestBody @NonNull UserDTO userDTO) {
        User user = userRepository.findByPhoneNumber(userDTO.getPhoneNumber());
        if (user == null) throw new IllegalArgumentException("手机号不存在");
        if (!new BCryptPasswordEncoder().matches(userDTO.getPassword(), user.getPassword()))
            throw new IllegalArgumentException("密码错误");
        var token = authService.generateTokenForUser(user);
        return token;
    }

    @Data
    public static class WechatLoginDTO {
        private String code;
    }

    /**
     * 用户注册
     *
     * @param userDTO
     * @return
     */
    @PostMapping("/signup")
    public String signup(@RequestBody @NonNull UserDTO userDTO) {
        if (userRepository.findByPhoneNumber(userDTO.getPhoneNumber()) != null)
            throw new IllegalArgumentException("手机号已被注册");
        authService.saveUser(userDTO, RoleConstants.ROLE_USER);
        return "注册成功";
    }

    @PostMapping("/update")
    public String update(CurrentAuth currentAuth, @RequestBody @NonNull UserUpdateDTO userUpdateDTO) {
        if (userUpdateDTO.getNewPassword().isBlank()) throw new IllegalArgumentException("新密码不能为空");
        if (userUpdateDTO.getOldPassword().isBlank()) throw new IllegalArgumentException("旧密码不能为空");
        // 因为用 userId 来充当 username，所以，这里的 username，其实就是 userId
        User user = currentAuth.getUser();
        if (!new BCryptPasswordEncoder().matches(userUpdateDTO.getOldPassword(), user.getPassword()))
            throw new IllegalArgumentException("旧密码不正确");
        user.setPassword(new BCryptPasswordEncoder().encode(userUpdateDTO.getNewPassword()));
        if (userUpdateDTO.getNickname() != null) {
            user.setNickname(userUpdateDTO.getNickname());
        }
        userRepository.save(user);
        return "修改成功";
    }

    @GetMapping("/info")
    public Object getInfo(CurrentAuth currentAuth) {
        User user = currentAuth.getUser();
        return new Object() {
            @Getter
            Long userId = user.getUserId();
            @Getter
            String nickname = user.getNickname();
            @Getter
            String phoneNumber = user.getPhoneNumber();
            @Getter
            String type = user.getType();
        };
    }
}
