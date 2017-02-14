package com.sombre.shop.utils.security;

import com.sombre.shop.models.pojo.dto.user.input.returnPassAndSalt.HashPasswordAndSalt;
import com.sombre.shop.models.pojo.entity.Users;
import org.mindrot.jbcrypt.BCrypt;

/**
 * Created by inna on 12.02.17.
 */
public class UserSecurity {

    public static String generateAccessToken(Users user) {
        return BCrypt.hashpw(user.getFirstname() + new String(user.getHashpassword())
                + BCrypt.gensalt(), BCrypt.gensalt(10));
    }

    public static HashPasswordAndSalt generateHashPassword(String password) {

        String encryptionSalt = BCrypt.gensalt(12);
        String hashPassword = BCrypt.hashpw(password, encryptionSalt);

        return new HashPasswordAndSalt(hashPassword, encryptionSalt);
    }

    public static boolean checkPassword(String password, byte[] truePassword) {

        return BCrypt.checkpw(password, new String(truePassword));

    }

}
