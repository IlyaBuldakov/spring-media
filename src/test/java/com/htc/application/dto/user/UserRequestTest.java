package com.htc.application.dto.user;

import static org.assertj.core.api.Assertions.assertThat;

import com.htc.domain.usecases.user.UserService;
import org.junit.jupiter.api.Test;

class UserRequestTest {

  @Test
  void shouldInitializeAllField() {
    // Arrange
    var user = UserService.createTestUser();

    // Act
    var userDto = new UserRequest(user);

    // Assert
    assertThat(userDto.getName()).isEqualTo(user.getName());
    assertThat(userDto.getEmail()).isEqualTo(user.getEmail());
    assertThat(userDto.getPassword()).isEqualTo(user.getPassword());
    assertThat(userDto.getImage()).isEqualTo(user.getImage());
    assertThat(userDto.getRole()).isEqualTo(user.getRole());
  }
}
