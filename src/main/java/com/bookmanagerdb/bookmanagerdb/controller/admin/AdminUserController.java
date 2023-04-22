package com.bookmanagerdb.bookmanagerdb.controller.admin;

import com.bookmanagerdb.bookmanagerdb.dao.UserRepository;
import com.bookmanagerdb.bookmanagerdb.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 管理员用户管理
 */
@RestController
@RequestMapping("/admin")
public class AdminUserController {
    @Autowired
    UserRepository userRepository;

    @ResponseBody
    @GetMapping("/user")
    public List<User> selectAllUser(@RequestParam("id") @Nullable Long id){
        if(id == null){
            return userRepository.findAll();
        }
        User user = new User();
        user.setUserId(id);
        return userRepository.findById(user.getUserId()).stream().toList();
    }

    @PutMapping("/user")
    public String updateById(@RequestBody @NonNull User user) throws Exception {
        User res=userRepository.findById(user.getUserId()).get();
        if(res==null) {
            throw new Exception("没有此用户");
        }
        //允许管理员更新的内容
        res.setNickname(user.getNickname());
        res.setType(user.getType());
        userRepository.save(res);
        return "更新成功";
    }

    @DeleteMapping("/user")
    public String deleteById(@RequestParam("id") @NonNull Long id) throws Exception {
        User res=userRepository.findById(id).get();
        if(res==null){
            throw new Exception("没有此用户");
        }
        userRepository.deleteById(res.getUserId());
        return "删除用户成功";
    }

}
