package com.htc.application.dtos.user;

import static org.assertj.core.api.Assertions.assertThat;

import com.htc.utilily.UserService;
import org.junit.jupiter.api.Test;

class UserShortResponseTest {
  @Test
  void shouldInitializeAllFields() {
    // Arrange
    var user = UserService.createTestUser();
    // Act
    var userDto = new UserShortResponse(user);
    // Assert
    assertThat(userDto.getId()).isEqualTo(user.getId());
    assertThat(userDto.getName()).isEqualTo(user.getName());
  }
}
