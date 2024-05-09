package app.service;

import app.database.DBChecks;
import app.entity.Users;
import app.entity.UserMappers;
import app.exceptions.DBExceptions;
import app.exceptions.UserExceptions;
import app.repository.impl.UserRepositorys;
import app.utils.Constantss;
import app.utils.UserValidator;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;




public class UserServices {

    UserRepositorys repository;
    private static final Logger LOGGER =
            Logger.getLogger(UserServices.class.getName());

    public UserServices(UserRepositorys repository) {
        this.repository = repository;
    }

    public String create(Map<String, String> data) {
        // Перевіряємо наявність файлу БД.
        // ТАК - працюємо з даними.
        // НІ - повідомлення про відсутність БД.
        if ( DBChecks.isDBExists() ) {
            try {
                throw new DBExceptions(Constantss.DB_ABSENT_MSG);
            } catch (DBExceptions e) {
                LOGGER.log(Level.SEVERE, Constantss.LOG_DB_ABSENT_MSG);
                return e.getMessage();
            }
        }
        Map<String, String> errors =
                new UserValidator().validateUserData(data);
        if ( !errors.isEmpty() ) {
            try {
                throw new UserExceptions("Check inputs", errors);
            } catch (UserExceptions e) {
                LOGGER.log(Level.WARNING, Constantss.LOG_DATA_INPTS_WRONG_MSG);
                return e.getErrors(errors);
            }
        }
        return repository.create(new UserMappers().mapUserData(data));
    }

    public String read() {
        // Перевіряємо наявність файлу БД.
        // ТАК - працюємо з даними.
        // НІ - повідомлення про відсутність БД.
        if ( DBChecks.isDBExists() ) {
            try {
                throw new DBExceptions(Constantss.DB_ABSENT_MSG);
            } catch (DBExceptions e) {
                LOGGER.log(Level.SEVERE, Constantss.LOG_DB_ABSENT_MSG);
                return e.getMessage();
            }
        }
        // Отримуємо дані
        Optional<List<Users>> optional = repository.read();
        // Якщо Optional не містить null, формуємо виведення.
        // Інакше повідомлення про відсутність даних.
        if ( optional.isPresent() ) {
            // Отримуємо колекцію з Optional
            List<Users> list = optional.get();
            // Якщо колекція не порожня, формуємо виведення.
            // Інакше повідомлення про відсутність даних.
            if ( !list.isEmpty() ) {
                AtomicInteger count = new AtomicInteger(0);
                StringBuilder sb = new StringBuilder();
                list.forEach((contact) ->
                        sb.append(count.incrementAndGet())
                                .append(") ")
                                .append(contact.toString())
                                .append("\n")
                );
                return sb.toString();
            } else {
                LOGGER.log(Level.WARNING, Constantss.LOG_DATA_ABSENT_MSG);
                return Constantss.DATA_ABSENT_MSG;
            }
        } else {
            LOGGER.log(Level.WARNING, Constantss.LOG_DATA_ABSENT_MSG);
            return Constantss.DATA_ABSENT_MSG;
        }
    }

    public String readById(Map<String, String> data) {
        // Перевіряємо наявність файлу БД.
        // ТАК - працюємо з даними.
        // НІ - повідомлення про відсутність БД.
        if ( DBChecks.isDBExists() ) {
            try {
                throw new DBExceptions(Constantss.DB_ABSENT_MSG);
            } catch (DBExceptions e) {
                LOGGER.log(Level.SEVERE, Constantss.LOG_DB_ABSENT_MSG);
                return e.getMessage();
            }
        }
        Map<String, String> errors =
                new UserValidator().validateUserData(data);
        if ( !errors.isEmpty() ) {
            try {
                throw new UserExceptions("Check inputs", errors);
            } catch (UserExceptions e) {
                LOGGER.log(Level.WARNING, Constantss.LOG_DATA_INPTS_WRONG_MSG);
                return e.getErrors(errors);
            }
        }
        // Отримуємо дані
        Optional<Users> optional =
                repository.readById(Long.parseLong(data.get("id")));
        // Якщо Optional не містить null, формуємо виведення.
        // Інакше повідомлення про відсутність даних.
        if ( optional.isPresent() ) {
            // Отримуємо об'єкт з Optional
            Users user = optional.get();
            return user.toString();
        } else {
            LOGGER.log(Level.WARNING, Constantss.LOG_DATA_ABSENT_MSG);
            return Constantss.DATA_ABSENT_MSG;
        }
    }

    public String update(Map<String, String> data) {
        // Перевіряємо наявність файлу БД.
        // ТАК - працюємо з даними.
        // НІ - повідомлення про відсутність БД.
        if ( DBChecks.isDBExists() ) {
            try {
                throw new DBExceptions(Constantss.DB_ABSENT_MSG);
            } catch (DBExceptions e) {
                LOGGER.log(Level.SEVERE, Constantss.LOG_DB_ABSENT_MSG);
                return e.getMessage();
            }
        }
        Map<String, String> errors =
                new UserValidator().validateUserData(data);
        if ( !errors.isEmpty() ) {
            try {
                throw new UserExceptions("Check inputs", errors);
            } catch (UserExceptions e) {
                LOGGER.log(Level.WARNING, Constantss.LOG_DATA_INPTS_WRONG_MSG);
                return e.getErrors(errors);
            }
        }
        // Спершу перевіряємо наявність об'єкта в БД за таким id.
        // Якщо ні, повертаємо повідомлення про відсутність таких даних,
        // інакше оновлюємо відповідний об'єкт в БД
        Users user = new UserMappers().mapUserData(data);
        if ( repository.readById(user.getId()).isEmpty() ) {
            return Constantss.DATA_ABSENT_MSG;
        } else {
            return repository.update(user);
        }
    }
    public String delete(Map<String, String> data) {

        if ( DBChecks.isDBExists()) {
            try {
                throw new DBExceptions(Constantss.DB_ABSENT_MSG);
            } catch (DBExceptions e) {
                LOGGER.log(Level.SEVERE, Constantss.LOG_DB_ABSENT_MSG);
                return e.getMessage();
            }
        }
        Map<String, String> errors =
                new UserValidator().validateUserData(data);
        if (!errors.isEmpty()) {
            try {
                throw new UserExceptions("Check inputs", errors);
            } catch (UserExceptions e) {
                LOGGER.log(Level.WARNING, Constantss.LOG_DATA_INPTS_WRONG_MSG);
                return e.getErrors(errors);
            }
        }
        // Спершу перевіряємо наявність такого id в БД.
        // Якщо ні, повертаємо повідомлення про відсутність
        // таких даних в БД, інакше видаляємо відповідний об'єкт
        // із БД.
        Long id = new UserMappers().mapUserData(data).getId();
        if (!repository.isIdExists(id)) {
            return Constantss.DATA_ABSENT_MSG;
        } else {
            return repository.delete(id);
        }
    }
}
