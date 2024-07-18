package com.example.unittestingcourse.testDoubleFundamentals.exercise4;

import com.example.unittestingcourse.testDoubleFundamentals.exercise4.networking.UserProfileHttpEndpointSync;
import com.example.unittestingcourse.testDoubleFundamentals.exercise4.users.User;
import com.example.unittestingcourse.testDoubleFundamentals.exercise4.users.UsersCache;

public class FetchUserProfileUseCaseSync {

    public enum UseCaseResult {
        SUCCESS,
        FAILURE,
        NETWORK_ERROR,

    }

    private final UserProfileHttpEndpointSync mUserProfileHttpEndpointSync;
    private final UsersCache mUsersCache;

    public FetchUserProfileUseCaseSync(UserProfileHttpEndpointSync userProfileHttpEndpointSync,
                                       UsersCache usersCache) {
        mUserProfileHttpEndpointSync = userProfileHttpEndpointSync;
        mUsersCache = usersCache;
    }

    public UseCaseResult fetchUserProfileSync(String userId) {
        UserProfileHttpEndpointSync.EndpointResult endpointResult;
        try {
            // the bug here is that userId is not passed to endpoint
            endpointResult = mUserProfileHttpEndpointSync.getUserProfile(userId);
            // the bug here is that I don't check for successful result and it's also a duplication
            // of the call later in this method

            if (isSuccessfulEndpointResult(endpointResult)) {
                mUsersCache.cacheUser(
                        new User(userId, endpointResult.getFullName(), endpointResult.getImageUrl()));
            }
            else {
                return UseCaseResult.FAILURE;
            }

            //mUsersCache.cacheUser(
                  //  new User(userId, endpointResult.getFullName(), endpointResult.getImageUrl()));
        } catch (
                com.example.unittestingcourse.testDoubleFundamentals.example4.networking.NetworkErrorException e) {
            return UseCaseResult.NETWORK_ERROR;
        }

        // the bug here is that I return wrong result in case of an unsuccessful server response
        return UseCaseResult.SUCCESS;
    }

    private boolean isSuccessfulEndpointResult(UserProfileHttpEndpointSync.EndpointResult endpointResult) {
        return endpointResult.getStatus() == UserProfileHttpEndpointSync.EndpointResultStatus.SUCCESS;
    }
}
