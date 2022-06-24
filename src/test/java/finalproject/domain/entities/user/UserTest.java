//package finalproject.domain.entities.user;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import finalproject.infrastructure.FakeUserRepository;
//import org.assertj.core.api.Assert;
//import org.junit.jupiter.api.Test;
//
//import java.util.Objects;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//
//class UserTest {
//  static ObjectMapper mapper = new ObjectMapper();
//  static void toConsole(Object obj) throws JsonProcessingException {
//    System.out.println(mapper.writeValueAsString(obj));
//    return;
//  }
//
//  @Test
//  void Test() throws JsonProcessingException {
//    FakeUserRepository repository = new FakeUserRepository();
//    repository.save(User.createRandomFakeUser());
//    repository.save(User.createRandomFakeUser());
//    repository.save(User.createRandomFakeUser());
//    repository.save(User.createRandomFakeUser());
//    repository.deleteById(2);
//
//    toConsole(repository.findAll());
//    User user1 = repository.findById(1).get();
//    user1.setName("Василий Хренов");
//    toConsole(repository.findAll());;
//    repository.save(user1);
//    toConsole(repository.findAll());
//
//
//
//  }
//
//
//    //void User_get_email() {
//    //assertThat(user.getEmail()).isEqualTo("ava@mail.ru");
//
//}