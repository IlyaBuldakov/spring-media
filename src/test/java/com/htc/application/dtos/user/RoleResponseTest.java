package com.htc.application.dtos.user;

import static org.assertj.core.api.Assertions.assertThat;

import com.htc.application.dtos.DataTransferObject;
import com.htc.utilily.UserService;
import org.junit.jupiter.api.Test;


class RoleResponseTest {
  @Test
  void shouldInheritDataTransferObject() {
    var role = UserService.createTestRole();
    var roleDto = new RoleResponse(role);
    assertThat(roleDto).isInstanceOf(DataTransferObject.class);
  }

  @Test
  void shouldInitializeAllFields() {
    var role = UserService.createTestRole();
    var roleDto = new RoleResponse(role);
    assertThat(roleDto.getId()).isEqualTo(role.getId());
    assertThat(roleDto.getName()).isEqualTo(role.getName());
  }
}
