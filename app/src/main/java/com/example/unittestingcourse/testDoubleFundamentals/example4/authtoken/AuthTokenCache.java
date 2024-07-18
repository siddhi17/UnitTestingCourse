package com.example.unittestingcourse.testDoubleFundamentals.example4.authtoken;

public interface AuthTokenCache {

    void cacheAuthToken(String authToken);

    String getAuthToken();
}
