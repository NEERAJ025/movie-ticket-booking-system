package com.nkediya.test.bookmyshow.user.UserService;

import com.nkediya.test.bookmyshow.user.UserDomain.User;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService{

    private final Map<String, User> userMap = new HashMap<>();

    @Override
    public User createUser(User user) {
        if (user.getUserId() == null || user.getUserId().isEmpty()) {
            user.setUserId(UUID.randomUUID().toString());
        }
        userMap.put(user.getUserId(), user);
        return user;
    }
    @Override
    public User getById(String userId) {
        return Optional.ofNullable(userMap.get(userId))
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
