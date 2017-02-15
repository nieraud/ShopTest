package com.sombre.shop.models.services.userDaoService;

import com.sombre.shop.models.factory.AbstractDaoFactory;
import com.sombre.shop.models.pojo.dto.user.input.UserUpdate;
import com.sombre.shop.models.pojo.dto.user.output.UserForAddingToDB;
import com.sombre.shop.models.pojo.entity.Users;
import com.sombre.shop.models.repositories.userRepository.UserRepository;
import com.sombre.shop.models.services.AbstractDaoService;
import org.sql2o.Connection;
import org.sql2o.Sql2oException;

import java.util.UUID;

/**
 * Created by inna on 11.02.17.
 */
public class UserDaoService extends AbstractDaoService implements UserRepository {
    public UserDaoService(AbstractDaoFactory daoFactory) {
        super(daoFactory);
    }

    @Override
    public boolean registration(UserForAddingToDB user) {

        String sql = "INSERT INTO users VALUES (DEFAULT, :firstname, :lastname, :birthday, " +
                ":phonenumber, DEFAULT, :useremail, :hashpassword, :encryptionsalt, NULL);";

        try (Connection connection = daoFactory.getDataSource().beginTransaction()) {

            connection.createQuery(sql, false)
                    .addParameter("firstname", user.getFirstname())
                    .addParameter("lastname", user.getLastname())
                    .addParameter("birthday", user.getBirthday())
                    .addParameter("phonenumber", user.getPhonenumber())
                    .addParameter("useremail", user.getUseremail())
                    .addParameter("hashpassword", user.getHashpassword())
                    .addParameter("encryptionsalt", user.getEncryptionsalt())
                    .executeUpdate();
            connection.commit();
            return true;
        } catch (Sql2oException e) {
            throw new Sql2oException(e);
        }
    }

    @Override
    public Users getUserByUserEmail(String userEmail) {

        String sql = "SELECT * FROM users WHERE useremail = :email;";

        try (Connection connection = daoFactory.getDataSource().open()) {

            return connection.createQuery(sql, false)
                    .addParameter("email", userEmail)
                    .executeAndFetchFirst(Users.class);

        } catch (Sql2oException e) {
            throw new Sql2oException(e);
        }
    }

    @Override
    public boolean authorization(String accessToken, UUID userID) {

        String sql = "UPDATE users SET accesstoken = :token WHERE uniqueid = :id;";

        try (Connection connection = daoFactory.getDataSource().open()) {

            connection.createQuery(sql, false)
                    .addParameter("token", accessToken.getBytes())
                    .addParameter("id", userID)
                    .executeUpdate();
            return true;

        } catch (Sql2oException e) {
            throw new Sql2oException(e);
        }
    }

    @Override
    public Users getUserByAccessToken(String token) {

        String sql = "SELECT * FROM users WHERE accesstoken = :token;";

        try (Connection connection = daoFactory.getDataSource().open()) {

            Users user = connection.createQuery(sql, false)
                    .addParameter("token", token.getBytes())
                    .executeAndFetchFirst(Users.class);

            if (user != null) return user;
            else return null;

        } catch (Sql2oException e) {
            throw new Sql2oException(e);
        }
    }

    @Override
    public String getAccessTokenByUserId(UUID userId) {

        String sql = "SELECT * FROM users WHERE uniqueid = :id;";

        try (Connection connection = daoFactory.getDataSource().open()) {

            Users user = connection.createQuery(sql, false)
                    .addParameter("id", userId)
                    .executeAndFetchFirst(Users.class);

            if (user.getAccesstoken() == null) return null;
            return new String(user.getAccesstoken());

        } catch (Sql2oException e) {
            throw new Sql2oException(e);
        }
    }

    @Override
    public boolean deleteUser(UUID userId) {
        String sql = "DELETE FROM users WHERE uniqueid = :id;";

        try (Connection connection = daoFactory.getDataSource().open()) {

            connection.createQuery(sql, false)
                    .addParameter("id", userId)
                    .executeUpdate();
            return true;

        } catch (Sql2oException e) {
            throw new Sql2oException(e);
        }
    }

    @Override
    public boolean updateUser(UserUpdate user) {
        String sql = "UPDATE users SET (firstname, lastname, birthday, phonenumber) = " +
                "(:firstname, :lastname, :birthday, :phonenumber) " +
                "WHERE uniqueid = :id;";

        try (Connection connection = daoFactory.getDataSource().open()) {

            connection.createQuery(sql, false)
                    .addParameter("firstname", user.getFirstname())
                    .addParameter("lastname", user.getLastname())
                    .addParameter("birthday", user.getBirthday())
                    .addParameter("phonenumber", user.getPhonenumber())
                    .executeUpdate();
            return true;

        } catch (Sql2oException e) {
            throw new Sql2oException(e);
        }
    }

    @Override
    public Users getUserById(UUID userId) {

        String sql = "SELECT * FROM users WHERE uniqueid = :id;";

        try (Connection connection = daoFactory.getDataSource().open()) {

            Users user = connection.createQuery(sql, false)
                    .addParameter("id", userId)
                    .executeAndFetchFirst(Users.class);

            if (user != null) return user;
            else return null;

        } catch (Sql2oException e) {
            throw new Sql2oException(e);
        }
    }


}
