package app.utils;

import app.controller.AppControllers;
import app.service.AppServices;
import app.view.AppViews;

public class AppStarters {

    public static void startApp() {
        AppServices service = new AppServices();
        AppViews view = new AppViews();
        AppControllers controller = new AppControllers(view, service);
        controller.runApp();
    }

}
