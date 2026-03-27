package com.nkediya.bookmyshow.user.UserService;

import com.nkediya.bookmyshow.user.UserDomain.User;

public interface UserService {
    User createUser(User user);
    User getById(String userId);

}
