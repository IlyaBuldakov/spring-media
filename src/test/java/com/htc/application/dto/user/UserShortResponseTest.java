package com.htc.application.dto.user;

import static org.assertj.core.api.Assertions.assertThat;

import com.htc.domain.usecases.user.UserService;
import org.junit.jupiter.api.Test;

class UserShortResponseTest {

  @Test
  void shouldInitializeAllField() {
    // Arrange
    var user = UserService.createTestUser();

    // Act
    var userDto = new UserShortResponse(user);

    // Assert
    assertThat(userDto.getId()).isEqualTo(user.getId());
    assertThat(userDto.getName()).isEqualTo(user.getName());
  }
}
