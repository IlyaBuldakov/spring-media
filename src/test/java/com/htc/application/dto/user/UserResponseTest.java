package com.htc.application.dto.user;

import static org.assertj.core.api.Assertions.assertThat;

import com.htc.domain.usecases.user.UserService;
import org.junit.jupiter.api.Test;

class UserResponseTest {

  @Test
  void shouldInitializeAllField() {
    // Arrange
    var user = UserService.createTestUser();

    // Act
    var userDto = new UserResponse(user);

    // Assert
    assertThat(userDto.getId()).isEqualTo(user.getId());
    assertThat(userDto.getName()).isEqualTo(user.getName());
    assertThat(userDto.getEmail()).isEqualTo(user.getEmail());
    assertThat(userDto.getImage()).isEqualTo(user.getImage());
    assertThat(userDto.getRole()).isEqualTo(user.getRole());
  }
}
