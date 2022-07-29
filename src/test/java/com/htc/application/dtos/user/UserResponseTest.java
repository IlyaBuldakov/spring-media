package com.htc.application.dtos.user;

import static org.assertj.core.api.Assertions.assertThat;

import com.htc.domain.entities.user.Role;
import com.htc.domain.entities.utility.parameters.EntityName;
import com.htc.domain.entities.utility.parameters.user.UserEmail;
import com.htc.domain.entities.utility.parameters.user.UserImage;
import com.htc.domain.entities.utility.parameters.user.UserPassword;
import com.htc.domain.usecases.user.AddUser;
import com.htc.infrastructure.models.user.UserModel;
import java.util.Random;
import org.junit.jupiter.api.Test;

class UserResponseTest {
  final AddUser.Params params = new AddUser.Params(
          EntityName.create("name").get(),
          UserEmail.create("email@email.com").get(),
          UserPassword.create("password11AA").get(),
          UserImage.create("image==").get(),
          Role.ADMIN
  );

  @Test
  void shouldInitializeAllFields() {
    // Arrange
    var userModel = new UserModel(
            new Random().nextLong(1, 32),
            params.name().getValue(),
            params.password().getValue(),
            params.email().getValue(),
            params.image().getValue(),
            params.role().getName());
    // Act
    var userDto = new UserResponse(userModel);
    // Assert
    assertThat(userDto.getId()).isEqualTo(userModel.getId().getValue());
    assertThat(userDto.getName()).isEqualTo(userModel.getName().getValue());
    assertThat(userDto.getEmail()).isEqualTo(userModel.getEmail().getValue());
    assertThat(userDto.getAvatar()).isEqualTo(userModel.getAvatar().getValue());
    assertThat(userDto.getRole()).isEqualTo(new RoleResponse(userModel.getRole()));
  }
}
