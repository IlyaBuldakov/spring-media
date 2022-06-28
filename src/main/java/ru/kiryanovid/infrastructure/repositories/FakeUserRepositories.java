package ru.kiryanovid.infrastructure.repositories;

import com.github.javafaker.Faker;
import org.springframework.stereotype.Component;
import ru.kiryanovid.domain.entity.users.Role;
import ru.kiryanovid.domain.entity.users.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

@Component
public class FakeUserRepositories {
    private static final Faker faker = Faker.instance(new Locale("ru"));
    private static final List<User> userList = new ArrayList<>();

    static {
        var roles = Role.values();
        var size = new Random().nextInt(10);
        while (size-- > 0){
            var user = User.create(
                    size,
                    faker.name().fullName(),
                    faker.internet().emailAddress(),
                    faker.internet().password(5, 17) + "1aA",
                    faker.lorem().characters(40),
                    roles[new Random().nextInt(roles.length)]
            ).get();
            userList.add(user);
        }
    }
    public List<User> getList(){
        return userList;
    }
}
