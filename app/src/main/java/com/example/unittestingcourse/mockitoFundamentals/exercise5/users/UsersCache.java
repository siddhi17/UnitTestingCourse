package com.example.unittestingcourse.mockitoFundamentals.exercise5.users;

public interface UsersCache {

    void cacheUser(User user);

    User getUser(String userId);

}
