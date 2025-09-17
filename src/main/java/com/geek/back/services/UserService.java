package com.geek.back.services;

import com.geek.back.models.User;

public interface UserService extends Service<User> {
    User createWithRoleNames(User user, java.util.Set<String> roleNames);
}