package com.htc.application.services.impl;

import com.htc.application.dto.user.UserRequest;
import com.htc.application.dto.user.UserResponse;
import com.htc.domain.entities.user.Role;
import com.htc.domain.entities.user.User;
import com.htc.domain.usecases.user.CreateUser;
import com.htc.domain.usecases.user.DeleteUserById;
import com.htc.domain.usecases.user.GetAllUsers;
import com.htc.domain.usecases.user.GetUserById;
import com.htc.domain.usecases.user.UpdateUser;
import io.vavr.control.Either;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class UsersServiceImplTest {

    private final UsersServiceImpl USERS_SERVICE =
            new UsersServiceImpl(mock(CreateUser.class), mock(GetUserById.class), mock(GetAllUsers.class),
                                 mock(UpdateUser.class), mock(DeleteUserById.class));

    // Валидные данные пользователя
    private final String ID = "1";
    private final String USERNAME = "Тестовый пользователь";
    private final String PASSWORD = "1aaaAAAaaa1";
    private final String EMAIL = "mail@mail.ru";
    private final String AVATAR = "SGVsbG9Xb3JsZA==";
    private final Role ROLE = Role.MANAGER;

    private final UserRequest USER_REQUEST =
            new UserRequest(USERNAME, PASSWORD, EMAIL, AVATAR, ROLE);

    private final User MOCK_USER = mock(User.class);

    // Установка поведения для заглушек UseCase'ов
    @BeforeEach
    public void beforeEach() {
        when(USERS_SERVICE.getAllUsers.execute()).thenReturn(
                CompletableFuture.completedFuture(
                        Either.right(List.of(MOCK_USER))
                )
        );
        when(USERS_SERVICE.getUserById.execute(ID)).thenReturn(
                CompletableFuture.completedFuture(Either.right(MOCK_USER))
        );
        when(USERS_SERVICE.createUser.execute(USERNAME, PASSWORD, EMAIL, AVATAR, ROLE)).thenReturn(
                CompletableFuture.completedFuture(Either.right(MOCK_USER))
        );
        when(USERS_SERVICE.updateUser.execute(ID, USERNAME, PASSWORD, EMAIL, AVATAR, ROLE)).thenReturn(
                CompletableFuture.completedFuture(Either.right(MOCK_USER))
        );
    }

    @Test
    public void getAll_shouldReturnListOfUserResponse() throws ExecutionException, InterruptedException {
        var retVal = USERS_SERVICE.getAll();
        UserResponse listElement = retVal.get().get(0);

        assertThat(retVal).isNotNull();
        assertThat(listElement).isInstanceOf(UserResponse.class);
    }

    @Test
    public void getAll_shouldCallUseCaseExecuteMethod() {
        USERS_SERVICE.getAll();
        verify(USERS_SERVICE.getAllUsers).execute();
    }

    @Test
    public void getById_correctId_shouldReturnUserResponse() throws ExecutionException, InterruptedException {
        var retVal = USERS_SERVICE.getById(ID);

        assertThat(retVal).isNotNull();
        assertThat(retVal.get()).isInstanceOf(UserResponse.class);
    }

    @Test
    public void getById_shouldCallUseCaseExecuteMethod_withInitialParams() {
        USERS_SERVICE.getById(ID);
        verify(USERS_SERVICE.getUserById).execute(ID);
    }

    @Test
    public void create_correctUserRequest_shouldReturnUserResponse() throws ExecutionException, InterruptedException {
        var retVal = USERS_SERVICE.create(USER_REQUEST);

        assertThat(retVal).isNotNull();
        assertThat(retVal.get()).isInstanceOf(UserResponse.class);
    }

    @Test
    public void create_shouldCallUseCaseExecuteMethod_withInitialParams() {
        USERS_SERVICE.create(USER_REQUEST);
        verify(USERS_SERVICE.createUser).execute(USERNAME, PASSWORD, EMAIL, AVATAR, ROLE);
    }

    @Test
    public void update_correctParams_shouldReturnUserResponse() throws ExecutionException, InterruptedException {
        var retVal = USERS_SERVICE.update(USER_REQUEST, ID);

        assertThat(retVal).isNotNull();
        assertThat(retVal.get()).isInstanceOf(UserResponse.class);
    }

    @Test
    public void update_shouldCallUseCaseExecuteMethod_withInitialParams() {
        USERS_SERVICE.update(USER_REQUEST, ID);
        verify(USERS_SERVICE.updateUser).execute(ID, USERNAME, PASSWORD, EMAIL, AVATAR, ROLE);
    }

    @Test
    public void deleteId_shouldCallUseCaseExecuteMethod_withInitialParams() {
        USERS_SERVICE.delete(ID);
        verify(USERS_SERVICE.deleteUserById).execute(ID);
    }
}