package com.example.unittestingcourse.mockitoFundamentals.example7.authtoken;

public interface AuthTokenCache {

    void cacheAuthToken(String authToken);

    String getAuthToken();
}
