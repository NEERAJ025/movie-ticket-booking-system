package com.nkediya.bookmyshow.user.service;

import com.nkediya.bookmyshow.user.domain.User;

public interface UserService {
    User createUser(User user);
    User getById(String userId);

}
