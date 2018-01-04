package com.example.pageablecustomsortdemo.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.pageablecustomsortdemo.domain.User;

public interface UserService {
    
    Page<User> getUsers(Pageable pageable);

}
