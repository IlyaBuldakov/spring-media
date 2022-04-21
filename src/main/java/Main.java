import domain.entity.users.Role;
import domain.entity.users.RoleDto;
import domain.entity.users.UserDto;

public class Main {
    public static void main(String[] args) {
        RoleDto manger = new RoleDto(1, Role.MANAGER);
        byte[] b = {1,2,3};
        UserDto user = new UserDto(1,"User1", "123@qwe.ru",b, manger);

        System.out.println(user);
    }
}
