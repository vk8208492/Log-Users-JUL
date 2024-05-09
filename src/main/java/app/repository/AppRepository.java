package app.repository;

import java.util.List;
import java.util.Optional;

public interface AppRepository<User> {
    String create(app.entity.User user);

    Optional<List<app.entity.User>> read();

    Optional<app.entity.User> readById(Long id);

    String update(app.entity.User user);

    String delete(Long id);

    // Перевірка наявності певного id у БД
    boolean isIdExists(Long id);
}
