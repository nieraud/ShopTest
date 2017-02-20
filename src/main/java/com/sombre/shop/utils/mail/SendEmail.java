package com.sombre.shop.utils.mail;

/**
 * Created by inna on 12.02.17.
 */
public class SendEmail {

    private static Sender tlsSenderOwner = new Sender("inusinka20@gmail.com", "zbochiniashka12");

    public static boolean sendGmail( ) throws Exception {
        try {
            tlsSenderOwner.send("header","content", "usernameAuthor", "userReceiver");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception();
        }
    }
}
