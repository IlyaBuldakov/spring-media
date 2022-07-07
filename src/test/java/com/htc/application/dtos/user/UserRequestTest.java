package com.htc.application.dtos.user;

import static org.assertj.core.api.Assertions.assertThat;

import com.htc.domain.entities.user.Role;
import com.htc.domain.usecases.user.AddUser;
import org.junit.jupiter.api.Test;

class UserRequestTest {
  final AddUser.Params params = new AddUser.Params(
          "name", "nameKey",
          "email@email.com", "emailKey",
          "password11AA", "passwordKey",
          "image==", "imageKey",
          Role.ADMIN, "roleKey"
  );

  @Test
  void shouldInitializeAllFields() {
    // Act
    var userDto = new UserRequest(
            params.name(),
            params.email(),
            params.password(),
            params.image(),
            params.role()
    );
    // Assert
    assertThat(userDto.getName()).isEqualTo(params.name());
    assertThat(userDto.getEmail()).isEqualTo(params.email());
    assertThat(userDto.getPassword()).isEqualTo(params.password());
    assertThat(userDto.getAvatar()).isEqualTo(params.image());
    assertThat(userDto.getRole()).isEqualTo(params.role());
  }
}
