package app.controller;

import app.service.UserService;
import app.utils.AppStarter;
import app.utils.Constants;
import app.view.UserViews;

import java.util.Map;

public class UserController {


    UserViews view;
    UserService service;

    public UserController(UserService service, UserViews view) {
        this.service = service;
        this.view = view;
    }

    public void createContact() {
        // Отримуємо вхідні дані
        Map<String, String> data = view.getCreateData();
        // Передаємо дані на обробку та отримуємо результат
        String res = service.create(data);
        // Перевіряємо результат щодо обробки даних.
        // Якщо БД відсутня, виводимо повідомлення про це
        // і закриваємо програму.
        // Інакше виводимо результат та перезапускаємо програму.
        if (res.equals(Constants.DB_ABSENT_MSG)) {
            // Виводимо результат
            view.getOutput(res);
            // Закриваємо програму
            System.exit(0);
        } else {
            // Виводимо результат
            view.getOutput(res);
            // Перезапускаємо програму
            AppStarter.startApp();
        }
    }

    public void readContacts() {
        // Отримаємо результат запиту в БД
        String res = service.read();
        // Перевіряємо результат щодо запиту в БД.
        // Якщо БД відсутня, виводимо повідомлення про це
        // і закриваємо програму.
        // Інакше виводимо результат та перезапускаємо програму.
        if (res.equals(Constants.DB_ABSENT_MSG)) {
            // Виводимо результат
            view.getOutput(res);
            // Закриваємо програму
            System.exit(0);
        } else {
            // Виводимо результат
            view.getOutput("\nCONTACTS:\n" + res);
            // Перезапускаємо програму
            AppStarter.startApp();
        }
    }

    public void readContactById() {
        // Отримуємо вхідні дані
        Map<String, String> data = view.getByIDData();
        // Передаємо дані на обробку та отримуємо результат
        String res = service.readById(data);
        // Перевіряємо результат щодо запиту в БД.
        // Якщо БД відсутня, виводимо повідомлення про це
        // і закриваємо програму.
        // Інакше виводимо результат та перезапускаємо програму.
        if (res.equals(Constants.DB_ABSENT_MSG)) {
            // Виводимо результат
            view.getOutput(res);
            // Закриваємо програму
            System.exit(0);
        } else {
            // Виводимо результат
            view.getOutput("\nCONTACT BY ID:\n" + res);
            // Перезапускаємо програму
            AppStarter.startApp();
        }
    }

    public void updateContact() {
        // Отримуємо вхідні дані
        Map<String, String> data = view.getUpdateData();
        // Передаємо дані на обробку та отримуємо результат
        String res = service.update(data);
        // Перевіряємо результат щодо обробки даних.
        // Якщо БД відсутня, виводимо повідомлення про це
        // і закриваємо програму.
        // Інакше виводимо результат та перезапускаємо програму.
        if (res.equals(Constants.DB_ABSENT_MSG)) {
            // Виводимо результат
            view.getOutput(res);
            // Закриваємо програму
            System.exit(0);
        } else {
            // Виводимо результат
            view.getOutput(res);
            // Перезапускаємо програму
            AppStarter.startApp();
        }
    }

    public void deleteContact() {
        // Отримуємо вхідні дані
        Map<String, String> data = view.getDeleteData();
        // Передаємо дані на обробку та отримуємо результат
        String res = service.delete(data);
        // Перевіряємо результат щодо обробки даних.
        // Якщо БД відсутня, виводимо повідомлення про це
        // і закриваємо програму.
        // Інакше виводимо результат та перезапускаємо програму.
        if (res.equals(Constants.DB_ABSENT_MSG)) {
            // Виводимо результат
            view.getOutput(res);
            // Закриваємо програму
            System.exit(0);
        } else {
            // Виводимо результат
            view.getOutput(res);
            // Перезапускаємо програму
            AppStarter.startApp();
        }
    }

}