package app.database;

import app.utils.Constantss;
import java.io.File;

public class DBChecks {

    public static boolean isDBExists() {
        return !new File(Constantss.DB_BASE_URL +
                Constantss.DB_NAME).exists();
    }
}
