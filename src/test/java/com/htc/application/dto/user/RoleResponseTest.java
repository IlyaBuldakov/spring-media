package com.htc.application.dto.user;

import static org.assertj.core.api.Assertions.assertThat;

import com.htc.domain.usecases.user.UserService;
import org.junit.jupiter.api.Test;

class RoleResponseTest {

  @Test
  void shouldInitializeAllField() {
    // Arrange
    var role = UserService.getRandomRole();

    // Act
    var roleDto = new RoleResponse(role);

    // Assert
    assertThat(roleDto.getId()).isEqualTo(role.getId());
    assertThat(roleDto.getName()).isEqualTo(role.getName());
  }
}
