package com.sombre.shop.utils.mail;

import model.pojo.entity.Post;
import model.pojo.entity.User;

/**
 * Created by User on 25.11.2016.
 */
public class SendEmail {

    private static Sender tlsSender = new Sender("inusinka20@gmail.com", "zbochiniashka12");

    public static boolean sendGmail(Post post, String usernameAuthorPost, User user) {
        try {
            tlsSender.send(post.getHeader(), post.getContent(), usernameAuthorPost, user.getUsername());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
