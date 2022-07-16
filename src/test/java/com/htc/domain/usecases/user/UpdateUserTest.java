package com.htc.domain.usecases.user;

import com.htc.domain.entities.failures.AlreadyExists;
import com.htc.domain.entities.failures.InvalidValuesContainer;
import com.htc.domain.entities.failures.NotFound;
import com.htc.domain.entities.user.Role;
import com.htc.domain.entities.user.User;
import com.htc.domain.repositories.UsersRepository;
import io.vavr.control.Either;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

public class UpdateUserTest {

    // Валидные данные пользователя.
    private final String ID = "1";
    private final String USERNAME = "Тестовый пользователь";
    private final String PASSWORD = "1aaaAAAaaa1";
    private final String EMAIL = "mail@mail.ru";
    private final String AVATAR = "SGVsbG9Xb3JsZA==";
    private final Role ROLE = Role.MANAGER;

    private final UsersRepository MOCK_USERS_REPOSITORY = mock(UsersRepository.class);

    private final UpdateUser UPDATE_USER = new UpdateUser(MOCK_USERS_REPOSITORY);

    private final User MOCK_USER = mock(User.class);

    @BeforeEach
    public void beforeEach() {
        when(MOCK_USERS_REPOSITORY.update(Integer.parseInt(ID), USERNAME, PASSWORD, EMAIL, AVATAR, ROLE))
                .thenReturn(CompletableFuture.completedFuture(Either.right(MOCK_USER)));
    }

    @Test
    public void shouldCallUsersRepositoryMethod_withInitialParams() {
        UPDATE_USER.execute(ID, USERNAME, PASSWORD, EMAIL, AVATAR, ROLE);
        verify(MOCK_USERS_REPOSITORY).update(Integer.parseInt(ID), USERNAME, PASSWORD, EMAIL, AVATAR, ROLE);
    }

    @Test
    public void validParams_shouldReturnUser() throws ExecutionException, InterruptedException {
        var retVal = UPDATE_USER.execute(ID, USERNAME, PASSWORD, EMAIL, AVATAR, ROLE);

        assertThat(retVal.get().get()).isNotNull().isInstanceOf(User.class);
    }

    @Test
    public void invalidId_shouldReturnInvalidValuesContainer_aboutId() throws ExecutionException, InterruptedException {
        var retVal = UPDATE_USER.execute("abc", USERNAME, PASSWORD,
                EMAIL, AVATAR, ROLE);

        var expectedFailure = retVal.get().getLeft();
        assertThat(expectedFailure).isNotNull().isInstanceOf(InvalidValuesContainer.class);

        var invalidValuesContainer = (InvalidValuesContainer) expectedFailure;
        assertThat(invalidValuesContainer.getInvalidValues().get(0).getField()).isEqualTo("id");
    }

    @Test
    public void invalidUserName_shouldReturnInvalidValuesContainer_aboutUserName() throws ExecutionException, InterruptedException {
        var retVal = UPDATE_USER.execute(ID, "", PASSWORD,
                EMAIL, AVATAR, ROLE);

        var expectedFailure = retVal.get().getLeft();
        assertThat(expectedFailure).isNotNull().isInstanceOf(InvalidValuesContainer.class);

        var invalidValuesContainer = (InvalidValuesContainer) expectedFailure;
        assertThat(invalidValuesContainer.getInvalidValues().get(0).getField()).isEqualTo("name");
    }

    @Test
    public void invalidPassword_shouldReturnInvalidValuesContainer_aboutPassword() throws ExecutionException, InterruptedException {
        var retVal = UPDATE_USER.execute(ID, USERNAME, "",
                EMAIL, AVATAR, ROLE);

        var expectedFailure = retVal.get().getLeft();
        assertThat(expectedFailure).isNotNull().isInstanceOf(InvalidValuesContainer.class);

        var invalidValuesContainer = (InvalidValuesContainer) expectedFailure;
        assertThat(invalidValuesContainer.getInvalidValues().get(0).getField()).isEqualTo("password");
    }

    @Test
    public void invalidEmail_shouldReturnInvalidValuesContainer_aboutEmail() throws ExecutionException, InterruptedException {
        var retVal = UPDATE_USER.execute(ID, USERNAME, PASSWORD,
                "", AVATAR, ROLE);

        var expectedFailure = retVal.get().getLeft();
        assertThat(expectedFailure).isNotNull().isInstanceOf(InvalidValuesContainer.class);

        var invalidValuesContainer = (InvalidValuesContainer) expectedFailure;
        assertThat(invalidValuesContainer.getInvalidValues().get(0).getField()).isEqualTo("email");
    }

    @Test
    public void invalidAvatar_shouldReturnInvalidValuesContainer_aboutAvatar() throws ExecutionException, InterruptedException {
        var retVal = UPDATE_USER.execute(ID, USERNAME, PASSWORD,
                EMAIL, "", ROLE);

        var expectedFailure = retVal.get().getLeft();
        assertThat(expectedFailure).isNotNull().isInstanceOf(InvalidValuesContainer.class);

        var invalidValuesContainer = (InvalidValuesContainer) expectedFailure;
        assertThat(invalidValuesContainer.getInvalidValues().get(0).getField()).isEqualTo("avatar");
    }

    @Test
    public void userNotFound_shouldReturnNotFound() throws ExecutionException, InterruptedException {
        when(MOCK_USERS_REPOSITORY.update(Integer.parseInt(ID), USERNAME, PASSWORD, EMAIL, AVATAR, ROLE))
                .thenReturn(CompletableFuture.completedFuture(Either.left(NotFound.USER)));
        var retVal = UPDATE_USER.execute(ID, USERNAME, PASSWORD, EMAIL, AVATAR, ROLE);

        assertThat(retVal.get().getLeft()).isNotNull().isInstanceOf(NotFound.class);
    }
}