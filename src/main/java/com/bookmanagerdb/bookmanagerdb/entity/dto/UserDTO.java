package com.bookmanagerdb.bookmanagerdb.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDTO {
    private String nickname;
    private String password;
    private String phoneNumber;
}
