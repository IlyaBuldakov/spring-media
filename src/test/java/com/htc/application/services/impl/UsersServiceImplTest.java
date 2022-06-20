package com.htc.application.services.impl;

import com.htc.application.dto.user.UserRequest;
import com.htc.domain.entities.user.User;
import com.htc.domain.repositories.UsersRepository;
import com.htc.util.Users;
import io.vavr.control.Either;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.CompletableFuture;

// Mock-и для проверки вызова соответствующего метода UseCase'а
import static com.htc.domain.usecases.user.CreateUserTest.mockCreateUser;
import static com.htc.domain.usecases.user.DeleteUserByIdTest.mockDeleteUserById;
import static com.htc.domain.usecases.user.GetAllUsersTest.mockGetAllUsers;
import static com.htc.domain.usecases.user.GetUserByIdTest.mockGetUserById;
import static com.htc.domain.usecases.user.UpdateUserTest.mockUpdateUser;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author IlyaBuldakov
 */
class UsersServiceImplTest {

    static UsersServiceImpl usersServiceImpl
            = new UsersServiceImpl(mockCreateUser, mockGetUserById, mockGetAllUsers,
            mockUpdateUser, mockDeleteUserById);
    static UsersRepository usersRepository = mock(UsersRepository.class);

    static User testUser = Users.createTestUser();

    static String testIdParam = "1";

    @BeforeAll
    static void before() {
        when(usersRepository.getAll()).thenReturn(CompletableFuture.completedFuture(Either.right(List.of(
                Users.createTestUser(),
                Users.createTestUser(),
                Users.createTestUser()
        ))));

        when(mockGetAllUsers.execute(null)).thenCallRealMethod();
        when(mockGetAllUsers.setUsersRepository(usersRepository)).thenCallRealMethod();

        mockGetAllUsers.setUsersRepository(usersRepository);

        when(mockGetUserById.execute(testIdParam)).thenCallRealMethod();
        when(mockGetUserById.setUsersRepository(usersRepository)).thenCallRealMethod();

        mockGetUserById.setUsersRepository(usersRepository);

        when(mockCreateUser.execute(testUser)).thenCallRealMethod();
        when(mockCreateUser.setUsersRepository(usersRepository)).thenCallRealMethod();

        mockCreateUser.setUsersRepository(usersRepository);

        when(mockUpdateUser.execute(testUser)).thenCallRealMethod();
        when(mockUpdateUser.setUsersRepository(usersRepository)).thenCallRealMethod();

        mockUpdateUser.setUsersRepository(usersRepository);

        when(mockDeleteUserById.execute(testIdParam)).thenCallRealMethod();
        when(mockDeleteUserById.setUsersRepository(usersRepository)).thenCallRealMethod();

        mockDeleteUserById.setUsersRepository(usersRepository);
    }

    @Test
    void getAllUsers_ShouldCallUseCaseMethod() {
        usersServiceImpl.getAll();
        verify(mockGetAllUsers).execute(null);
    }

    @Test
    void getUserById_ShouldCallUseCaseMethod() {
        usersServiceImpl.getById(testIdParam);
        verify(mockGetUserById).execute(testIdParam);
    }

    @Test
    void createUser_ShouldCallUseCaseMethod() {
        usersServiceImpl.create(new UserRequest(testUser));
        verify(mockCreateUser).execute(testUser);
    }

    @Test
    void updateUser_ShouldCallUseCaseMethod() {
        usersServiceImpl.update(new UserRequest(testUser));
        verify(mockUpdateUser).execute(testUser);
    }

    @Test
    void deleteUser_ShouldCallUseCaseMethod() {
        usersServiceImpl.delete(testIdParam);
        verify(mockDeleteUserById).execute(testIdParam);
    }
}