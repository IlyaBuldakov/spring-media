package com.htc.application.dtos.user;

import static org.assertj.core.api.Assertions.assertThat;

import com.htc.domain.entities.user.Role;
import java.util.Random;
import org.junit.jupiter.api.Test;

class RoleResponseTest {
  @Test
  void shouldInitializeAllFields() {
    var role = Role.values()[new Random().nextInt(Role.values().length)];
    var roleDto = new RoleResponse(role);
    assertThat(roleDto.getId()).isEqualTo(role.getId());
    assertThat(roleDto.getName()).isEqualTo(role.getName());
  }
}
