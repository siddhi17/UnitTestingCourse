package com.example.unittestingcourse.mockitoFundamentals.exercise5;


import com.example.unittestingcourse.mockitoFundamentals.exercise5.eventbus.EventBusPoster;
import com.example.unittestingcourse.mockitoFundamentals.exercise5.eventbus.UserDetailsChangedEvent;
import com.example.unittestingcourse.mockitoFundamentals.exercise5.networking.NetworkErrorException;
import com.example.unittestingcourse.mockitoFundamentals.exercise5.networking.UpdateUsernameHttpEndpointSync;
import com.example.unittestingcourse.mockitoFundamentals.exercise5.users.User;
import com.example.unittestingcourse.mockitoFundamentals.exercise5.users.UsersCache;

public class UpdateUsernameUseCaseSync {

    public enum UseCaseResult {
        SUCCESS,
        FAILURE,
        NETWORK_ERROR
    }

    private final UpdateUsernameHttpEndpointSync mUpdateUsernameHttpEndpointSync;
    private final UsersCache mUsersCache;
    private final EventBusPoster mEventBusPoster;

    public UpdateUsernameUseCaseSync(UpdateUsernameHttpEndpointSync updateUsernameHttpEndpointSync,
                                     UsersCache usersCache,
                                     EventBusPoster eventBusPoster) {
        mUpdateUsernameHttpEndpointSync = updateUsernameHttpEndpointSync;
        mUsersCache = usersCache;
        mEventBusPoster = eventBusPoster;
    }

    public UseCaseResult updateUsernameSync(String userId, String username) {
        UpdateUsernameHttpEndpointSync.EndpointResult endpointResult = null;
        try {
            endpointResult = mUpdateUsernameHttpEndpointSync.updateUsername(userId, username);
        } catch (NetworkErrorException e) {
            // the bug here is "swallowed" exception instead of return
            return UseCaseResult.NETWORK_ERROR;
        }

        if (isSuccessfulEndpointResult(endpointResult)) {
            // the bug here is reversed arguments
            User user = new User(endpointResult.getUserId(),endpointResult.getUsername());
            mEventBusPoster.postEvent(new UserDetailsChangedEvent(new User(userId,username)));
            mUsersCache.cacheUser(user);
            return UseCaseResult.SUCCESS;
        } else {
            return UseCaseResult.FAILURE;
        }
    }

    private boolean isSuccessfulEndpointResult(UpdateUsernameHttpEndpointSync.EndpointResult endpointResult) {
        // the bug here is the wrong definition of successful response
        return endpointResult.getStatus() == UpdateUsernameHttpEndpointSync.EndpointResultStatus.SUCCESS;
    }
}