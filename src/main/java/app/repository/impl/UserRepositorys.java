package app.repository.impl;

import app.database.DBConns;
import app.entity.Users;
import app.repository.AppRepositorys;
import app.utils.Constantss;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserRepositorys implements AppRepositorys<Users> {

    private final static String TABLE_USERS = "users";
    private static final Logger LOGGER =
            Logger.getLogger(UserRepositorys.class.getName());

    @Override
    public String create(Users user) {

        String sql = "INSERT INTO " + TABLE_USERS + " (name, email) VALUES(?, ?)";

        try (PreparedStatement pstmt = DBConns.connect().prepareStatement(sql)) {

            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getEmail());

            pstmt.executeUpdate();

            return Constantss.DATA_INSERT_MSG;
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, Constantss.LOG_DB_ERROR_MSG);

            return e.getMessage();
        }
    }

    @Override
    public Optional<List<Users>> read() {
        try (Statement stmt = DBConns.connect().createStatement()) {

            List<Users> list = new ArrayList<>();

            String sql = "SELECT id, name, email FROM " + TABLE_USERS;

            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                list.add(new Users(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getString("email"))
                );
            }

            return Optional.of(list);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, Constantss.LOG_DB_ERROR_MSG);

            return Optional.empty();
        }
    }

    @Override
    public Optional<Users> readById(Long id) {

        String sql = "SELECT id, name, email FROM " + TABLE_USERS +
                " WHERE id = ?";
        try (PreparedStatement pst = DBConns.connect().prepareStatement(sql)) {
            pst.setLong(1, id);
            ResultSet rs = pst.executeQuery();
            rs.next();
            Users user = new Users();
            long entityId = rs.getLong("id");
            String name = rs.getString("name");
            String email = rs.getString("email");
            if (entityId == 0 | name == null | email == null)
                user = null;
            else {
                user.setId(entityId);
                user.setName(name);
                user.setEmail(email);
            }

            return Optional.ofNullable(user);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, Constantss.LOG_DB_ERROR_MSG);

            return Optional.empty();
        }
    }

    @Override
    public String update(Users user) {

        String sql = "UPDATE " + TABLE_USERS + " SET name = ?, email = ? WHERE id = ?";

        try (PreparedStatement pstmt = DBConns.connect().prepareStatement(sql)) {

            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getEmail());
            pstmt.setLong(3, user.getId());

            pstmt.executeUpdate();

            return Constantss.DATA_UPDATE_MSG;
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, Constantss.LOG_DB_ERROR_MSG);

            return e.getMessage();
        }
    }

    @Override
    public String delete(Long id) {

        String sql = "DELETE FROM " + TABLE_USERS + " WHERE id = ?";

        try (PreparedStatement stmt = DBConns.connect().prepareStatement(sql)) {

            stmt.setLong(1, id);

            stmt.executeUpdate();

            return Constantss.DATA_DELETE_MSG;
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, Constantss.LOG_DB_ERROR_MSG);

            return e.getMessage();
        }
    }


    @Override
    public boolean isIdExists(Long id) {
        String sql = "SELECT COUNT(id) FROM " + TABLE_USERS +
                " WHERE id = ?";
        try {
            PreparedStatement pst = DBConns.connect().prepareStatement(sql);
            pst.setLong(1, id);
            try (ResultSet rs = pst.executeQuery()) {

                if (rs.next()) {
                    return rs.getBoolean(1);
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, Constantss.LOG_DB_ERROR_MSG);
            return false;
        }
        return false;
    }

}
