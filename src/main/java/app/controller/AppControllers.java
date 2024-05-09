package app.controller;

import app.service.AppServices;
import app.view.AppViews;

public class AppControllers {

    AppViews view;
    AppServices service;

    public AppControllers(AppViews view, AppServices service) {
        this.view = view;
        this.service = service;
    }

    public void runApp() {
        service.handleOption(view.getOption());
    }
}
