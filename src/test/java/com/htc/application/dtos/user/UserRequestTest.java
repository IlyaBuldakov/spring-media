package com.htc.application.dtos.user;

import static org.assertj.core.api.Assertions.assertThat;

import com.htc.domain.entities.user.Role;
import com.htc.domain.entities.utility.parameters.EntityName;
import com.htc.domain.entities.utility.parameters.user.UserEmail;
import com.htc.domain.entities.utility.parameters.user.UserImage;
import com.htc.domain.entities.utility.parameters.user.UserPassword;
import com.htc.domain.usecases.user.AddUser;
import org.junit.jupiter.api.Test;

class UserRequestTest {
  final AddUser.Params params = new AddUser.Params(

          EntityName.create("name").get(),
          UserEmail.create("email@email.com").get(),
          UserPassword.create("password11AA").get(),
          UserImage.create("image==").get(),
          Role.ADMIN
  );

  @Test
  void shouldInitializeAllFields() {
    // Act
    var userDto = new UserRequest(
            params.name().getValue(),
            params.email().getValue(),
            params.password().getValue(),
            params.image().getValue(),
            params.role()
    );
    // Assert
    assertThat(userDto.getName()).isEqualTo(params.name().getValue());
    assertThat(userDto.getEmail()).isEqualTo(params.email().getValue());
    assertThat(userDto.getPassword()).isEqualTo(params.password().getValue());
    assertThat(userDto.getAvatar()).isEqualTo(params.image().getValue());
    assertThat(userDto.getRole()).isEqualTo(params.role());
  }
}
