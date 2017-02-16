package com.sombre.shop.utils.security;

import com.sombre.shop.models.pojo.dto.userDto.input.returnPassAndSalt.HashPasswordAndSaltDto;
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

    public static HashPasswordAndSaltDto generateHashPassword(String password) {

        String encryptionSalt = BCrypt.gensalt(12);
        String hashPassword = BCrypt.hashpw(password, encryptionSalt);

        return new HashPasswordAndSaltDto(hashPassword, encryptionSalt);
    }

    public static boolean checkPassword(String password, byte[] truePassword) {

        return BCrypt.checkpw(password, new String(truePassword));

    }

}
