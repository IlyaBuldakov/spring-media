package com.htc.application.dtos.user;

import static org.assertj.core.api.Assertions.assertThat;

import com.htc.domain.entities.user.Role;
import com.htc.domain.usecases.user.AddUser;
import com.htc.infrastructure.models.user.UserModel;
import java.util.Random;
import org.junit.jupiter.api.Test;

class UserShortResponseTest {
  final AddUser.Params params = new AddUser.Params(
          "name", "nameKey",
          "email@email.com", "emailKey",
          "password11AA", "passwordKey",
          "image==", "imageKey",
          Role.ADMIN, "roleKey"
  );

  @Test
  void shouldInitializeAllFields() {
    // Arrange
    var userm = new UserModel(
            new Random().nextLong(1, 32),
            params.name(),
            params.password(),
            params.email(),
            params.image(),
            params.role().getName());
    // Act
    var userDto = new UserShortResponse(userm);
    // Assert
    assertThat(userDto.getId()).isEqualTo(userm.getId().getValue());
    assertThat(userDto.getName()).isEqualTo(userm.getName().getValue());
  }
}
