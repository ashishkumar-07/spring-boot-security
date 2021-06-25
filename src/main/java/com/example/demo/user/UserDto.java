package com.example.demo.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDto {

    private final String userId;
    private final String userName;
    private final String email;
}
