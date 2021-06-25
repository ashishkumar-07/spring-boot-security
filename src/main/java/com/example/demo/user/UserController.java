package com.example.demo.user;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    private static final List<UserDto> USERS = Arrays.asList(
      new UserDto("60d306d5973d216ce9675f0e", "John", "John@gmail.com"),
      new UserDto("60d30732973d216ce9675f0f", "Johny", "Johny@gmail.com"),
      new UserDto("60d30765973d216ce9675f10", "Janardhan", "Janardhan@gmail.com")
    );

    @GetMapping(path = "{userId}")
    @PostAuthorize("returnObject !=null || returnObject.getUserName() == #principle.getName()") //user can only view it's pwn profile
    public UserDto getStudent(@PathVariable("userId") String userId, @AuthenticationPrincipal Principal principle) {
    	
    	System.out.println(principle);
        return USERS.stream()
                .filter(user -> userId.equals(user.getUserId()))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(
                        "User " + userId + " does not exists" //define global exception handler
                ));
    }
}
