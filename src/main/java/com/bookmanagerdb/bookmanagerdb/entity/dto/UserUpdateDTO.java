package com.bookmanagerdb.bookmanagerdb.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserUpdateDTO {
    private String oldPassword;
    private String nickname;
    private String newPassword;
}
