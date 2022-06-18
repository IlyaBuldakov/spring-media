package com.htc.application.dtos.user;

import static org.assertj.core.api.Assertions.assertThat;

import com.htc.utilily.UserService;
import org.junit.jupiter.api.Test;

class UserRequestTest {
  @Test
  void shouldInitializeAllFields() {
    // Arrange
    var user = UserService.createTestUser();
    // Act
    var userDto = new UserRequest(user);
    // Assert
    assertThat(userDto.getName()).isEqualTo(user.getName());
    assertThat(userDto.getEmail()).isEqualTo(user.getEmail());
    assertThat(userDto.getPassword()).isEqualTo(user.getPassword());
    assertThat(userDto.getAvatar()).isEqualTo(user.getAvatar());
    assertThat(userDto.getRole()).isEqualTo(user.getRole());
  }
}
