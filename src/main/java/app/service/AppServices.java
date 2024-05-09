package app.service;

import app.controller.UserControllers;
import app.exceptions.OptionExceptions;
import app.repository.impl.UserRepositorys;
import app.utils.AppStarters;
import app.utils.Constantss;
import app.view.AppViews;
import app.view.UserViews;

public class AppServices {

    UserRepositorys repository = new UserRepositorys();
    UserServices service = new UserServices(repository);
    UserViews view = new UserViews();
    UserControllers controller = new UserControllers(service, view);

    public void handleOption(int option) {
        switch (option) {
            case 1 -> controller.createContact();
            case 2 -> controller.readContacts();
            case 3 -> controller.updateContact();
            case 4 -> controller.deleteContact();
            case 5 -> controller.readContactById();
            case 0 -> new AppViews().getOutput(Integer.toString(option));
            default -> {
                try {
                    throw new OptionExceptions(Constantss.INCORRECT_OPTION_MSG);
                } catch (OptionExceptions e) {
                    new AppViews().getOutput(e.getMessage());
                    AppStarters.startApp();
                }
            }
        }
    }

}
