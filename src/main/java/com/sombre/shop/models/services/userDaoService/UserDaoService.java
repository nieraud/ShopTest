package com.sombre.shop.models.services.userDaoService;

import com.sombre.shop.models.factory.AbstractDaoFactory;
import com.sombre.shop.models.pojo.dto.user.output.UserForAddingToDB;
import com.sombre.shop.models.repositories.userRepository.UserRepository;
import com.sombre.shop.models.services.UserAbstractDaoService;
import org.joda.time.DateTime;
import org.sql2o.Connection;
import org.sql2o.Sql2oException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by inna on 11.02.17.
 */
public class UserDaoService extends UserAbstractDaoService implements UserRepository {

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

}
