package finalproject.domain.entities.user;

import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.*;


class UserTest {
  User user = new User("ava@mail.ru", "вася", "assdfasfsf", "abcdefghewwert1A", Role.ADMIN);

  @Test
  void User_get_email() {
    assertThat(user.getEmail()).isEqualTo("ava@mail.ru");
  }
}