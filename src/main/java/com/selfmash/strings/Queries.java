package com.selfmash.strings;

public class Queries {

    // For User entity
    public static final String QUERY_GET_USER_BY_LOGIN = "from User as u where u.login = :login";

    public static final String QUERY_GET_USER_BY_ID = "from User as u where u.id = :id";

    public static final String QUERY_SELECT_FRIENDS = "select * from user as u "
            + "inner join friends as f on f.friend_id = u.id and accepted = 1 "
            + "join user_role  as ur on ur.user_id = u.id where f.user_id = :userId "
            + "union select distinct * from user as u "
            + "inner join friends as f on f.user_id = u.id and accepted = 1  "
            + "join user_role  as ur on ur.user_id = u.id where f.friend_id = :userId";
    public static final String QUERY_ADD_FRIEND = "insert into friends values ( :userId, :friendId, 0)";
    
    public static final String QUERY_CONFIRM_FRIENDSHIP = "Update friends set accepted = 1 "
            + "where user_id = :userId and friend_id = :friendId";

    
    // For Notification entity
    public static final String QUERY_GET_NOTIFICATION_BY_ID = "from Notification as n where n.id = :notificationId";
    
    public static final String QUERY_GET_NOTIFICATIONS_BY_USER_ID = "select * from notification as n "
            + "join notification_user as nu "
            + "on nu.notification_id = n.id join sender_notification as sn"
            + " on sn.notification_id = n.id where nu.user_id = :userId";

    
}
