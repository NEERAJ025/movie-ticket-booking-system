package com.nkediya.test.bookmyshow.user.UserService;

import com.nkediya.test.bookmyshow.user.UserDomain.User;

public interface UserService {
    User createUser(User user);
    User getById(String userId);

}
