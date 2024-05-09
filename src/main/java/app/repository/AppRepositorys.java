package app.repository;

import app.entity.Users;

import java.util.List;
import java.util.Optional;

public interface AppRepositorys<User> {
    String create(Users user);

    Optional<List<Users>> read();

    Optional<Users> readById(Long id);

    String update(Users user);

    String delete(Long id);

    // Перевірка наявності певного id у БД
    boolean isIdExists(Long id);
}
