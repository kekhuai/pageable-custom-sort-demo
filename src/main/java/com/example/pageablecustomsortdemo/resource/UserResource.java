package com.example.pageablecustomsortdemo.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.pageablecustomsortdemo.domain.User;
import com.example.pageablecustomsortdemo.service.UserService;

@RestController
@RequestMapping(value = "/users/api/v1")
public class UserResource {
    
    private final UserService userService;

    @Autowired
    public UserResource(UserService userService) {
        super();
        this.userService = userService;
    }
    
    @GetMapping(produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public Page<User> getUsers(Pageable pageable) {
        return userService.getUsers(pageable);
    }

}
