package com.example.demo.user;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("management/api/v1/users")
public class UserManagementController {

    private static final List<UserDto> USERS = Arrays.asList(
    	      new UserDto("60d306d5973d216ce9675f0e", "John", "John@gmail.com"),
    	      new UserDto("60d30732973d216ce9675f0f", "Johny", "Johny@gmail.com"),
    	      new UserDto("60d30765973d216ce9675f10", "Janardhan", "Janardhan@gmail.com")
    );

//    hasRole('ROLE_') hasAnyRole('ROLE_') hasAuthority('permission') hasAnyAuthority('permission')

    @GetMapping
    @PreAuthorize("hasAnyAuthority('USER_READ', 'USER_WRITE')") 
    public List<UserDto> getAllUsers() {
        System.out.println("getAllUsers");
        return USERS;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('USER_WRITE')")
    public void registerNewUser(@RequestBody UserDto user) {
        System.out.println("registerNewUser");
        System.out.println(user);
    }

    @DeleteMapping(path = "{userId}")
    @PreAuthorize("hasAuthority('USER_WRITE')")
    public void deleteUser(@PathVariable("userId") String userId) {
        System.out.println("deleteUser");
        System.out.println(userId);
    }

    @PutMapping(path = "{userId}")
    @PreAuthorize("hasAuthority('USER_WRITE')")
    public void updateUser(@PathVariable("userId") String userId, @RequestBody UserDto user) {
        System.out.println("updateUser");
        System.out.println(String.format("%s %s", userId, user));
    }
}
