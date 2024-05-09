package app.database;

import app.utils.Constantss;
import app.view.AppViews;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBConns {

    private static final Logger LOGGER =
            Logger.getLogger(DBConns.class.getName());

    public static Connection connect() {

        AppViews appView = new AppViews();
        Connection conn = null;

        try {

            conn = DriverManager.getConnection(Constantss.DB_DRIVER +
                    Constantss.DB_BASE_URL + Constantss.DB_NAME);
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, Constantss.LOG_DB_ABSENT_MSG);

            appView.getOutput(e.getMessage());
        }
        return conn;
    }
}
