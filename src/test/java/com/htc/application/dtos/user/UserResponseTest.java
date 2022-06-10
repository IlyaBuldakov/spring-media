package com.htc.application.dtos.user;

import static org.assertj.core.api.Assertions.assertThat;

import com.htc.application.dtos.DataTransferObject;
import com.htc.utilily.UserService;
import org.junit.jupiter.api.Test;

class UserResponseTest {

  @Test
  void shouldInheritDataTransferObject() {
    var user = UserService.createTestUser();
    var userDto = new UserResponse(user);
    assertThat(userDto).isInstanceOf(DataTransferObject.class);
  }

  @Test
  void shouldInitializeAllFields() {
    // Arrange
    var user = UserService.createTestUser();
    // Act
    var userDto = new UserResponse(user);
    // Assert
    assertThat(userDto.getId()).isEqualTo(user.getId());
    assertThat(userDto.getName()).isEqualTo(user.getName());
    assertThat(userDto.getEmail()).isEqualTo(user.getEmail());
    assertThat(userDto.getAvatar()).isEqualTo(user.getAvatar());
    assertThat(userDto.getRole()).isEqualTo(user.getRole());
  }
}
