package app.utils;

import java.util.HashMap;
import java.util.Map;

import static app.utils.AppValidators.*;

public class UserValidator {

    public Map<String, String> validateUserData(Map<String, String> data) {

        Map<String, String> errors = new HashMap<>();

        if ( data.containsKey("id") & isIdValid(data.get("id")) )
            errors.put("id", Constantss.WRONG_ID_MSG);

        if ( data.containsKey("name") ) {
            if ( data.get("name") != null & data.get("name").isEmpty() )
                errors.put("name", Constantss.INPUT_REQ_MSG);
        }
        if ( data.containsKey("email") & AppValidators.isEmailValid(data.get("email")) )
            errors.put("email", Constantss.WRONG_EMAIL_MSG);

        return errors;
    }
}

