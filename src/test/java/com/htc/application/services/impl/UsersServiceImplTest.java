package com.htc.application.services.impl;

import com.htc.application.dto.user.UserRequest;
import com.htc.domain.entities.user.User;
import com.htc.domain.repositories.UsersRepository;
import com.htc.domain.usecases.user.CreateUser;
import com.htc.domain.usecases.user.DeleteUserById;
import com.htc.domain.usecases.user.GetAllUsers;
import com.htc.domain.usecases.user.GetUserById;
import com.htc.domain.usecases.user.UpdateUser;
import com.htc.util.Users;
import io.vavr.control.Either;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author IlyaBuldakov
 */
@ExtendWith(MockitoExtension.class)
class UsersServiceImplTest {

    // Arrange
    static GetAllUsers getAllUsers = mock(GetAllUsers.class);
    static GetUserById getUserById = mock(GetUserById.class);
    static CreateUser createUser = mock(CreateUser.class);
    static UpdateUser updateUser = mock(UpdateUser.class);
    static DeleteUserById deleteUserById = mock(DeleteUserById.class);

    static UsersServiceImpl usersServiceImpl = new UsersServiceImpl(createUser, getUserById, getAllUsers,
            updateUser, deleteUserById);
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

        when(getAllUsers.execute(null)).thenCallRealMethod();
        when(getAllUsers.setUsersRepository(usersRepository)).thenCallRealMethod();

        getAllUsers.setUsersRepository(usersRepository);

        when(getUserById.execute(testIdParam)).thenCallRealMethod();
        when(getUserById.setUsersRepository(usersRepository)).thenCallRealMethod();

        getUserById.setUsersRepository(usersRepository);

        when(createUser.execute(testUser)).thenCallRealMethod();
        when(createUser.setUsersRepository(usersRepository)).thenCallRealMethod();

        createUser.setUsersRepository(usersRepository);

        when(updateUser.execute(testUser)).thenCallRealMethod();
        when(updateUser.setUsersRepository(usersRepository)).thenCallRealMethod();

        updateUser.setUsersRepository(usersRepository);

        when(deleteUserById.execute(testIdParam)).thenCallRealMethod();
        when(deleteUserById.setUsersRepository(usersRepository)).thenCallRealMethod();

        deleteUserById.setUsersRepository(usersRepository);
    }

    @Test
    void getAllUsers_ShouldCallUseCaseMethod() {
        usersServiceImpl.getAll();
        verify(getAllUsers).execute(null);
    }

    @Test
    void getUserById_ShouldCallUseCaseMethod() {
        usersServiceImpl.getById(testIdParam);
        verify(getUserById).execute(testIdParam);
    }

    @Test
    void createUser_ShouldCallUseCaseMethod() {
        usersServiceImpl.create(new UserRequest(testUser));
        verify(createUser).execute(testUser);

    }

    @Test
    void updateUser_UserRequestInjected_ShouldReturnUserResponse() {
        usersServiceImpl.update(new UserRequest(testUser));
        verify(updateUser).execute(testUser);
    }

    @Test
    void deleteUser_StringIdInjected_ShouldReturn() {
        usersServiceImpl.delete(testIdParam);
        verify(deleteUserById).execute(testIdParam);
    }
}