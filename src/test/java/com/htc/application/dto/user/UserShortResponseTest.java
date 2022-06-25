package com.htc.application.dto.user;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.htc.application.dto.DataTransferObject;
import com.htc.utility.UserService;
import org.junit.jupiter.api.Test;

class UserShortResponseTest {

  @Test
  void shouldInheritUseCase() {
    // Arrange
    var user = UserService.createTestUser();
    var userDto = new UserShortResponse(user);

    // Assert
    assertThat(userDto).isInstanceOf(DataTransferObject.class);
  }

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