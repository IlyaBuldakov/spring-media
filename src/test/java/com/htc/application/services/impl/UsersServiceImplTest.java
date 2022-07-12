package com.htc.application.services.impl;

import com.htc.application.dto.user.UserRequest;
import com.htc.application.dto.user.UserResponse;
import com.htc.domain.entities.user.User;
import com.htc.util.Users;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UsersServiceImplTest {

    String testIdParam = "1";

    User testUser = Users.createTestUser();

    UserRequest testUserRequest = new UserRequest(testUser);

    UsersServiceImpl usersServiceImpl = mock(UsersServiceImpl.class);

    @Test
    void getAllShouldReturnListUserResponse() throws ExecutionException, InterruptedException {
        when(usersServiceImpl.getAll()).thenReturn(
                CompletableFuture.completedFuture(
                        List.of(new UserResponse(testUser),
                                new UserResponse(testUser),
                                new UserResponse(testUser))
                )
        );
        var retVal = usersServiceImpl.getAll();
        assertThat(retVal).isNotNull().isNotCancelled();
        assertThat(retVal.get().get(0)).isInstanceOf(UserResponse.class);
    }

    @Test
    void getByIdShouldReturnUserResponse() throws ExecutionException, InterruptedException {
        when(usersServiceImpl.getById(testIdParam)).thenReturn(
                CompletableFuture.completedFuture(new UserResponse(testUser))
        );
        var retVal = usersServiceImpl.getById(testIdParam);
        assertThat(retVal).isNotNull().isNotCancelled();
        assertThat(retVal.get()).isInstanceOf(UserResponse.class);
    }

    @Test
    void createShouldReturnUserResponse() throws ExecutionException, InterruptedException {
        when(usersServiceImpl.create(testUserRequest)).thenReturn(
                CompletableFuture.completedFuture(new UserResponse(testUser))
        );
        var retVal = usersServiceImpl.create(testUserRequest);
        assertThat(retVal).isNotNull().isNotCancelled();
        assertThat(retVal.get()).isInstanceOf(UserResponse.class);
    }

    @Test
    void updateShouldReturnUserResponse() throws ExecutionException, InterruptedException {
        when(usersServiceImpl.update(testUserRequest, testIdParam)).thenReturn(
                CompletableFuture.completedFuture(new UserResponse(testUser))
        );
        var retVal = usersServiceImpl.update(testUserRequest, testIdParam);
        assertThat(retVal).isNotNull().isNotCancelled();
        assertThat(retVal.get()).isInstanceOf(UserResponse.class);
    }

    @Test
    void deleteShouldReturnUserResponse() throws ExecutionException, InterruptedException {
        when(usersServiceImpl.delete(testIdParam)).thenReturn(
                CompletableFuture.completedFuture(new UserResponse(testUser))
        );
        var retVal = usersServiceImpl.delete(testIdParam);
        assertThat(retVal).isNotNull().isNotCancelled();
        assertThat(retVal.get()).isInstanceOf(UserResponse.class);
    }
}