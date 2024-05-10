package app.service;

import app.controller.UserController;
import app.exceptions.OptionException;
import app.repository.impl.UserRepository;
import app.utils.AppStarter;
import app.utils.Constants;
import app.view.AppView;
import app.view.UserViews;

public class AppService {

    UserRepository repository = new UserRepository();
    UserService service = new UserService(repository);
    UserViews view = new UserViews();
    UserController controller = new UserController(service, view);

    public void handleOption(int option) {
        switch (option) {
            case 1 -> controller.createContact();
            case 2 -> controller.readContacts();
            case 3 -> controller.updateContact();
            case 4 -> controller.deleteContact();
            case 5 -> controller.readContactById();
            case 0 -> new AppView().getOutput(Integer.toString(option));
            default -> {
                try {
                    throw new OptionException(Constants.INCORRECT_OPTION_MSG);
                } catch (OptionException e) {
                    new AppView().getOutput(e.getMessage());
                    AppStarter.startApp();
                }
            }
        }
    }

}
