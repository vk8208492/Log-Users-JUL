package app.entity;

import java.util.Map;
public class UserMappers {

    public Users mapUserData(Map<String, String> data) {
        Users user = new Users();
        if (data.containsKey("id"))
            user.setId(Long.parseLong(data.get("id")));
        if (data.containsKey("name"))
            user.setName(data.get("name"));
        if (data.containsKey("email"))
            user.setEmail(data.get("email"));
        return user;
    }
}
